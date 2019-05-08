package nyla.solutions.core.patterns.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import nyla.solutions.core.util.Debugger;

public class ProxyInvocationHandler implements InvocationHandler
{

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		Debugger.println("proxy="+method);
		return null;
	}

}
