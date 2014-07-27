dist/MyApplication.jar : src/plugins/hello/MyApplication.java src/plugins/hello/Overview.java
	ant

.PHONY: bones
bones : dist/BareBones.jar
dist/BareBones.jar : src/plugins/hello/BareBones.java
	ant bones
