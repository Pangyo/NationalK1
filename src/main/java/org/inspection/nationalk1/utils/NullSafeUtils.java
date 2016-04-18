package org.inspection.nationalk1.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * @author jgy0726@gmail.com on 
 * 2016. 4. 19.
 */
@Component
public class NullSafeUtils {

	static public <T> List<T> list(List<T> c) {
		return (c == null) ? Collections.<T>emptyList() : c;
	}
	
	static public <K,V> Map<K,V> map(Map<K,V> c) {
		return (c == null) ? Collections.<K,V>emptyMap() : c;
	}
		
	static public <T> Set<T> set(Set<T> c) {
		
		return (c == null) ? Collections.<T>emptySet() : c;
	}
}
