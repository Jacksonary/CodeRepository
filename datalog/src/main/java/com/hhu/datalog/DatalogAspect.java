package com.hhu.datalog;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.assertj.core.internal.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhu.domain.Action;
import com.hhu.domain.ActionType;
import com.hhu.domain.ChangeItem;
import com.hhu.util.DiffUtil;

@Aspect//标志他是个切面的类
@Component
public class DatalogAspect {

	private static final Logger logger = LoggerFactory.getLogger(DatalogAspect.class);
	
	@Autowired
	ActionDao actionDao;
	
	
	@Pointcut("execution(public * com.hhu.dao.*.save*(..))")
	public void save() {
		
	}
	
	@Pointcut("execution(public * com.hhu.dao.*.delete*(..))")
	public void delete() {
		
	}
	
	
	/*
	 * 定义完Pointcut,再定义Advice，即前后逻辑代码
	 * 这里使用的是@Around
	 * 它经典的写法就是try-catch-finally
	 * 
	 * 1.判断是什么类型的操作,增加\删除\还是更新
     *  增加/更新 save(Product),通过id区分是增加还是更新
     *  删除delete(id)
     * 2.获取changeitem
     *   (1)新增操作,before直接获取,after记录下新增之后的id
     *   (2)更新操作,before获取操作之前的记录,after获取操作之后的记录,然后diff获取ChangeItem
     *   (3)删除操作,before根据id取记录
     * 3.保存操作记录
     *    actionType
     *    objectId
     *    objectClass
	 */
	@Around("save() || delete()")
	public Object addOperateLog(ProceedingJoinPoint pjp) throws Throwable{
		Object returnObj = null;
		
		//获取操作方法名
		String method = pjp.getSignature().getName();
		System.out.println("***************用户当前再进行" + method + "操作***************");
		
		//初始化操作类型
		ActionType actionType = null;
		//初始化id，用户区分更新还是新增的行为
		Long id = null;
		Action action = new Action();
		
		Object oldObj = null;
		
		try {
			
			//判断方法是增、删、改中的哪一类
			if("save".equals(method)) {//如果方法有“save”则可能是save或者update
				//获取操作对象
				Object obj = pjp.getArgs()[0];
				System.out.println("***************操作对象为：" + obj + "***************");
				
				//获取对象的id属性
				Object idObj = PropertyUtils.getProperty(obj,"id");
				
				System.out.println("获取的idObj为： " + idObj);
				
				//注意：这里不能直接对idObj进行toString然后强转包装类，主要如果获取对象为null那么在进行toString就会报空指针异常
				
				if(idObj==null) {
					//如果没有id参数那么就是新增操作
					actionType = ActionType.INSERT;
					//在Insert是获取ChangeItems
					List<ChangeItem> changeItems = DiffUtil.getInsertChangeItems(obj);
					//存储在change里
					action.getChanges().addAll(changeItems);
					//这里存在疑问，新增操作前是不存在旧对象的，就为null，这里注意数据库是否接受的问题
//					action.setObjectClass(oldObj.getClass().getName());
				} else {
					
					//这里注意getProperty()获取的是Object对象
					id = Long.valueOf(idObj.toString());
					System.out.println("***************操作对象的id为：" + id + "***************");
					
					//如果出现id就是更新操作
					System.out.println("***************执行更新操作，id为：" + id + "***************");
					actionType = ActionType.UPDATE;
					action.setObjectId(id);
					
					//将旧的对象保存起来（即更新前的对象）
					oldObj = DiffUtil.getObjectById(pjp.getTarget(), id);
					//更新操作时是存在旧对象的
					action.setObjectClass(oldObj.getClass().getName());
				}
			} else if ("delete".equals(method)){
				//保存删除擦操作前对象的id，这里既然进行删除操作，那么id就不可能为空，所以这里可以安全转换
				id = Long.valueOf(pjp.getArgs()[0].toString());
				System.out.println("***************执行删除操作，id为：" + id + "***************");
				actionType = ActionType.DELETE;
				//保存删除前的对象
				oldObj = DiffUtil.getObjectById(pjp.getTarget(), id);
				ChangeItem changeItem = DiffUtil.getDeleteChangeItem(oldObj);
				action.getChanges().add(changeItem);
				action.setObjectClass(oldObj.getClass().getName());
				action.setObjectId(id);
			}
			
			
			
			/*执行proceed（）方法获取返回值，这里即方法的执行，这里要做的
			 * 就是在该方法前后加上非功能性代码
			 */
			returnObj = pjp.proceed(pjp.getArgs());
			
			
			
			//After之后的逻辑,即保存操记录
			action.setActionType(actionType);
			if(actionType.INSERT == actionType) {
				//获取新增后的id,通过反射获取returnObj中的id
				Object newId = PropertyUtils.getProperty(returnObj, "id");
				action.setObjectId(Long.valueOf(newId.toString()));
			} else if(ActionType.UPDATE ==actionType) {
				//获取新的对象
				Object newObj = DiffUtil.getObjectById(pjp.getTarget(), id);
				System.out.println("***************更新操作时获取的即将更新的对象为" + newObj + "***************");
				List<ChangeItem> changeItems = DiffUtil.getChangeItems(oldObj, newObj);
				//保存ChangeItems
				action.getChanges().addAll(changeItems);
			}
			
			action.setOperator("admin");//具体根据登录用户来设定
			action.setOperatorTime(new Date());
			
			actionDao.save(action);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		} 
		//返回返回值
		return returnObj;
	}
	
}
