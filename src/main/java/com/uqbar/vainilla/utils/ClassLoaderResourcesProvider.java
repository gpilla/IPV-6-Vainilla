package com.uqbar.vainilla.utils;

import java.net.URL;

public class ClassLoaderResourcesProvider implements ResourceProvider{
	
	private ClassLoader loader;

	public ClassLoaderResourcesProvider() {
		this(ClassLoaderResourcesProvider.class.getClassLoader());
	}

	public ClassLoaderResourcesProvider(ClassLoader loader) {
		this.loader = loader;
	}
	
	@Override
	public URL getResource(String name) throws Exception {
		return this.loader.getResource(name);
	}

}
