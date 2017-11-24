package com.hhu.entity;

/**
 * 用户信息，分为用户名和密码以及照片
 * 
 * @author Weiguo Liu
 *
 */
public class User {

	private int fId;
	private String gI;
	private String fCode;// 登录名
	private String fPwd;// 密码
	private String fCaption;// 职位
	private int fEnable;// 权限
	private String fMemo;
	private String fTel;// 电话
	private String fAddress;// 地址
	private int fOrgId;// 所属部门ID
	private String fSfz;
	private String fWx;
	private String fMail;// 邮箱

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getgI() {
		return gI;
	}

	public void setgI(String gI) {
		this.gI = gI;
	}

	public String getfCode() {
		return fCode;
	}

	public void setfCode(String fCode) {
		this.fCode = fCode;
	}

	public String getfPwd() {
		return fPwd;
	}

	public void setfPwd(String fPwd) {
		this.fPwd = fPwd;
	}

	public String getfCaption() {
		return fCaption;
	}

	public void setfCaption(String fCaption) {
		this.fCaption = fCaption;
	}

	public int getfEnable() {
		return fEnable;
	}

	public void setfEnable(int fEnable) {
		this.fEnable = fEnable;
	}

	public String getfMemo() {
		return fMemo;
	}

	public void setfMemo(String fMemo) {
		this.fMemo = fMemo;
	}

	public String getfTel() {
		return fTel;
	}

	public void setfTel(String fTel) {
		this.fTel = fTel;
	}

	public String getfAddress() {
		return fAddress;
	}

	public void setfAddress(String fAddress) {
		this.fAddress = fAddress;
	}

	public int getfOrgId() {
		return fOrgId;
	}

	public void setfOrgId(int fOrgId) {
		this.fOrgId = fOrgId;
	}

	public String getfSfz() {
		return fSfz;
	}

	public void setfSfz(String fSfz) {
		this.fSfz = fSfz;
	}

	public String getfWx() {
		return fWx;
	}

	public void setfWx(String fWx) {
		this.fWx = fWx;
	}

	public String getfMail() {
		return fMail;
	}

	public void setfMail(String fMail) {
		this.fMail = fMail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fAddress == null) ? 0 : fAddress.hashCode());
		result = prime * result + ((fCaption == null) ? 0 : fCaption.hashCode());
		result = prime * result + ((fCode == null) ? 0 : fCode.hashCode());
		result = prime * result + fEnable;
		result = prime * result + fId;
		result = prime * result + ((fMail == null) ? 0 : fMail.hashCode());
		result = prime * result + ((fMemo == null) ? 0 : fMemo.hashCode());
		result = prime * result + fOrgId;
		result = prime * result + ((fPwd == null) ? 0 : fPwd.hashCode());
		result = prime * result + ((fSfz == null) ? 0 : fSfz.hashCode());
		result = prime * result + ((fTel == null) ? 0 : fTel.hashCode());
		result = prime * result + ((fWx == null) ? 0 : fWx.hashCode());
		result = prime * result + ((gI == null) ? 0 : gI.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (fAddress == null) {
			if (other.fAddress != null)
				return false;
		} else if (!fAddress.equals(other.fAddress))
			return false;
		if (fCaption == null) {
			if (other.fCaption != null)
				return false;
		} else if (!fCaption.equals(other.fCaption))
			return false;
		if (fCode == null) {
			if (other.fCode != null)
				return false;
		} else if (!fCode.equals(other.fCode))
			return false;
		if (fEnable != other.fEnable)
			return false;
		if (fId != other.fId)
			return false;
		if (fMail == null) {
			if (other.fMail != null)
				return false;
		} else if (!fMail.equals(other.fMail))
			return false;
		if (fMemo == null) {
			if (other.fMemo != null)
				return false;
		} else if (!fMemo.equals(other.fMemo))
			return false;
		if (fOrgId != other.fOrgId)
			return false;
		if (fPwd == null) {
			if (other.fPwd != null)
				return false;
		} else if (!fPwd.equals(other.fPwd))
			return false;
		if (fSfz == null) {
			if (other.fSfz != null)
				return false;
		} else if (!fSfz.equals(other.fSfz))
			return false;
		if (fTel == null) {
			if (other.fTel != null)
				return false;
		} else if (!fTel.equals(other.fTel))
			return false;
		if (fWx == null) {
			if (other.fWx != null)
				return false;
		} else if (!fWx.equals(other.fWx))
			return false;
		if (gI == null) {
			if (other.gI != null)
				return false;
		} else if (!gI.equals(other.gI))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [fId=" + fId + ", gI=" + gI + ", fCode=" + fCode + ", fPwd=" + fPwd + ", fCaption=" + fCaption
				+ ", fEnable=" + fEnable + ", fMemo=" + fMemo + ", fTel=" + fTel + ", fAddress=" + fAddress
				+ ", fOrgId=" + fOrgId + ", fSfz=" + fSfz + ", fWx=" + fWx + ", fMail=" + fMail + "]";
	}

	// public int getfID() {
	// return fID;
	// }
	//
	// public void setfID(int f_ID) {
	// fID = f_ID;
	// }
	//
	// public String getGi() {
	// return Gi;
	// }
	//
	// public void setGi(String gi) {
	// Gi = gi;
	// }
	//
	// public String getfCode() {
	// return fCode;
	// }
	//
	// public void setfCode(String f_Code) {
	// fCode = f_Code;
	// }
	//
	// public String getfPwd() {
	// return fPwd;
	// }
	//
	// public void setfPwd(String f_Pwd) {
	// fPwd = f_Pwd;
	// }
	//
	// public String getfCaption() {
	// return fCaption;
	// }
	//
	// public void setfCaption(String f_Caption) {
	// fCaption = f_Caption;
	// }
	//
	// public int getfEnable() {
	// return fEnable;
	// }
	//
	// public void setfEnable(int f_Enable) {
	// fEnable = f_Enable;
	// }
	//
	// public String getfMemo() {
	// return fMemo;
	// }
	//
	// public void setfMemo(String f_Memo) {
	// fMemo = f_Memo;
	// }
	//
	// public String getfTel() {
	// return fTel;
	// }
	//
	// public void setfTel(String f_Tel) {
	// fTel = f_Tel;
	// }
	//
	// public String getfAddress() {
	// return fAddress;
	// }
	//
	// public void setfAddress(String f_Address) {
	// fAddress = f_Address;
	// }
	//
	// public int getOrgID() {
	// return OrgID;
	// }
	//
	// public void setOrgID(int orgID) {
	// OrgID = orgID;
	// }
	//
	// public String getfSFZ() {
	// return fSFZ;
	// }
	//
	// public void setfSFZ(String f_SFZ) {
	// fSFZ = f_SFZ;
	// }
	//
	// public String getfWx() {
	// return fWx;
	// }
	//
	// public void setfWx(String f_Wx) {
	// fWx = f_Wx;
	// }
	//
	// public String getfMail() {
	// return fMail;
	// }
	//
	// public void setfMail(String f_Mail) {
	// fMail = f_Mail;
	// }
	//
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((fAddress == null) ? 0 : fAddress.hashCode());
	// result = prime * result + ((fCaption == null) ? 0 : fCaption.hashCode());
	// result = prime * result + ((fCode == null) ? 0 : fCode.hashCode());
	// result = prime * result + fEnable;
	// result = prime * result + fID;
	// result = prime * result + ((fMail == null) ? 0 : fMail.hashCode());
	// result = prime * result + ((fMemo == null) ? 0 : fMemo.hashCode());
	// result = prime * result + ((fPwd == null) ? 0 : fPwd.hashCode());
	// result = prime * result + ((fSFZ == null) ? 0 : fSFZ.hashCode());
	// result = prime * result + ((fTel == null) ? 0 : fTel.hashCode());
	// result = prime * result + ((fWx == null) ? 0 : fWx.hashCode());
	// result = prime * result + ((Gi == null) ? 0 : Gi.hashCode());
	// result = prime * result + OrgID;
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// User other = (User) obj;
	// if (fAddress == null) {
	// if (other.fAddress != null)
	// return false;
	// } else if (!fAddress.equals(other.fAddress))
	// return false;
	// if (fCaption == null) {
	// if (other.fCaption != null)
	// return false;
	// } else if (!fCaption.equals(other.fCaption))
	// return false;
	// if (fCode == null) {
	// if (other.fCode != null)
	// return false;
	// } else if (!fCode.equals(other.fCode))
	// return false;
	// if (fEnable != other.fEnable)
	// return false;
	// if (fID != other.fID)
	// return false;
	// if (fMail == null) {
	// if (other.fMail != null)
	// return false;
	// } else if (!fMail.equals(other.fMail))
	// return false;
	// if (fMemo == null) {
	// if (other.fMemo != null)
	// return false;
	// } else if (!fMemo.equals(other.fMemo))
	// return false;
	// if (fPwd == null) {
	// if (other.fPwd != null)
	// return false;
	// } else if (!fPwd.equals(other.fPwd))
	// return false;
	// if (fSFZ == null) {
	// if (other.fSFZ != null)
	// return false;
	// } else if (!fSFZ.equals(other.fSFZ))
	// return false;
	// if (fTel == null) {
	// if (other.fTel != null)
	// return false;
	// } else if (!fTel.equals(other.fTel))
	// return false;
	// if (fWx == null) {
	// if (other.fWx != null)
	// return false;
	// } else if (!fWx.equals(other.fWx))
	// return false;
	// if (Gi == null) {
	// if (other.Gi != null)
	// return false;
	// } else if (!Gi.equals(other.Gi))
	// return false;
	// if (OrgID != other.OrgID)
	// return false;
	// return true;
	// }
	//
	// @Override
	// public String toString() {
	// return "User [fID=" + fID + ", Gi=" + Gi + ", fCode=" + fCode + ", fPwd="
	// + fPwd + ", fCaption="
	// + fCaption + ", fEnable=" + fEnable + ", fMemo=" + fMemo + ", fTel=" +
	// fTel + ", fAddress="
	// + fAddress + ", OrgID=" + OrgID + ", fSFZ=" + fSFZ + ", fWx=" + fWx + ",
	// fMail=" + fMail + "]";
	// }

}
