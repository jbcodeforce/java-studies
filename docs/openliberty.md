# OpenLiberty notes

### Specific open liberty maven declaration

```
<parent>
	<groupId>net.wasdev.wlp.maven.parent</groupId>
	<artifactId>liberty-maven-app-parent</artifactId>
	<version>RELEASE</version>
</parent>
```

Properties:
```
<app.name>${project.artifactId}</app.name>
<testServerHttpPort>9080</testServerHttpPort>
<testServerHttpsPort>9443</testServerHttpsPort>
<warContext>${app.name}</warContext>
<package.file>${project.build.directory}/${app.name}.zip</package.file>
<packaging.type>usr</packaging.type>
```

### Open liberty links

* [Open Liberty developer tool for eclipse](https://marketplace.eclipse.org/category/free-tagging/open-liberty)
* [Super guides](https://openliberty.io/guides/)
* [Understanding the liberty maven plugin](https://developer.ibm.com/wasdev/docs/installing-liberty-liberty-maven-plug/)

### Debug Openliberty app

Start `mvn liberty:debug`, the console should display the port number, it listens to (7777). Then in Eclipse define a debud configuration for a `remote java application`, use localhost and the matching port number. Any breakpoint in the code should be reachable.

For VS code:
