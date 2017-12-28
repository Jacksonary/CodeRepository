package com.hhu.matlab;

import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import myCrabNeuralNetworkFunction2.CrabClassfity;

/**
 * 测试螃蟹识别的网络
 * created by WeiguoLiu on 2017/12/28
 */
public class CrabTest {
    public static void main(String[] args) throws MWException {

        //定义为行向量
        Double[][] inputs2 = new Double[][]{{0.0, 20.6, 14.4, 42.8, 46.5, 19.6}};
        //这个类的生成的Matlab中只支持行向量的输入形式
        CrabClassfity crabClassfity2 = new CrabClassfity();
        //这个生成的matlab函数不支持列向量，所以用列向量的形式会报错
        Object[] result = crabClassfity2.myCrabNeuralNetworkFunction2(1, inputs2);
        for (Object r : result) {
            System.out.println(r.toString());
        }
        System.out.println("=========x-y都支持的向量输入形式=========");

        myCrabNeuralNetworkFunction.CrabClassfity crabClassfity = new myCrabNeuralNetworkFunction.CrabClassfity();
        /*涉及到矩阵的转置问题，直接定义为二维矩阵,这里定义是matlab函数的
        输入矩阵向量，注意输入的形式，是原来的矩阵还是转置之后的矩阵，如下
        是定义的列向量
         */
        Double[][] inputs = new Double[][]{
                {0.0},
                {20.6},
                {14.4},
                {42.8},
                {46.5},
                {19.6},
        };
        MWNumericArray array = new MWNumericArray(inputs);
        Object[] targets = crabClassfity.myCrabNeuralNetworkFunction(3,array);
        for(Object t:targets) {
            System.out.println(t.toString());
        }
    }
}
