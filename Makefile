dist/MyApplication.jar : src/plugins/hello/MyApplication.java src/plugins/hello/Overview.java build.xml
	ant

.PHONY: bones
bones : dist/BareBones.jar
dist/BareBones.jar : src/plugins/hello/BareBones.java build.xml
	ant bones
