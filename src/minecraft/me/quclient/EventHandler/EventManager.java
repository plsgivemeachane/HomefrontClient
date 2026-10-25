package me.quclient.EventHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.events.EventTarget;

import me.quclient.Client.Client;
import me.quclient.Utils.Loader;

public class EventManager {
	
	private class ClassMethod {
		
		public Class c,caller;
		public Method m;
		public Object callee;
		
		
		public ClassMethod(Class c, Method m, Class caller, Object callee) {
			this.c = c;
			this.m = m;
			this.caller = caller;
			this.callee = callee;
		}
		
	}
	
	private Client instance;
	//private HashMap<Class, Method> events = new HashMap<Class, Method>();
	private List<ClassMethod> events = new ArrayList<ClassMethod>();
	private List<Event> eventList = new ArrayList<Event>();
	
	public EventManager(Client instance) {
		this.instance = instance;
	}
	
	public void setup() {
		events.clear();
	}
	
	public void registerEvent(Event e) {
		eventList.add(e);
	}
	
	public void registerMethod(Object c) {
		
		//System.out.println(c);
		Method[] methods = c.getClass().getMethods();
		for(Method m : methods) {
			//System.out.println(m);
			if(m.isAnnotationPresent(EventListener.class)) {
				System.out.println("Found : " + m.toString());
				Parameter[] paramaters = m.getParameters();
				
				for(Parameter p : paramaters) {
					if(p.getType().getSuperclass() == Event.class || p.getType().getSuperclass() == EventCancelable.class) {
						System.out.println("REGISTED : " + p.toString());
						events.add(new ClassMethod(p.getType(),m,c.getClass(),c));
					}
				}
			}
		}
	}
	
	public void unregisterMethod(Object c) {
		//System.out.println(c);
		
		for(ClassMethod cm : events) {
			if(cm.c == c.getClass()) {
				events.remove(c);
				System.out.println("UNREGISTED " + c.toString());
			}
		}
		
	}
	
	public void callEvent(Event e) {
		List<ClassMethod> lists = new ArrayList<ClassMethod>();
		for(final EventPriolity i : EventPriolity.values()) {
			for(ClassMethod cm : events) {
				if(cm.c == e.getClass() && cm.m.getAnnotation(EventListener.class) != null) {
					if(((EventListener)cm.m.getAnnotation(EventListener.class)).value() == i)
						//System.out.println("Method " + cm.m.toString() + " Priolity " + i.toString());
						lists.add(cm);
				}
			}
		}
		
		for(ClassMethod cm : lists) {
			try {
				cm.m.invoke(cm.callee, e);
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

}
