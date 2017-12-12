package com.hhu.common.tree;

public class NodeAttribute {
	
	private String url;
	private Long id;
	
	public NodeAttribute(String url, Long id) {
		super();
		this.url = url;
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
