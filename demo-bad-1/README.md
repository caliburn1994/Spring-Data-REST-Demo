I have tried to implement **Versioning through URI Path** in **Spring Data Rest**, but I failed finally.  Spring Data Rest is not like Spring Boot Web.

## Difficulty-1

I have used `RequestMappingHandlerMapping` and `annotation` to filter the handler,but

```java
@Override
	protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {

		HandlerMethod handlerMethod = super.lookupHandlerMethod(lookupPath, request);

		if (handlerMethod == null) {
			return null;
		}

		String repositoryLookupPath = new BaseUri(configuration.getBasePath()).getRepositoryLookupPath(lookupPath);

		// Repository root resource
		if (!StringUtils.hasText(repositoryLookupPath)) {
			return handlerMethod;
		}

		String repositoryBasePath = getRepositoryBasePath(repositoryLookupPath);

		if (!mappings.exportsTopLevelResourceFor(repositoryBasePath)) {
			return null;
		}

		exposeEffectiveLookupPathKey(handlerMethod, request, repositoryBasePath);

		return handlerMethod;
	}
```

`mappings.exportsTopLevelResourceFor(repositoryBasePath)` doesn't allow me to go over, and run `exposeEffectiveLookupPathKey`.

## Difficulty-2

`@RepositoryRestResource` only allow one  segment of path. And, you only could redirect or forward the whole Resource. You only can use filter or other something to do that.