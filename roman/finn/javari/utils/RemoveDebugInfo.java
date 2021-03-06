package roman.finn.javari.utils;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.util.*;

public class RemoveDebugInfo implements Processor {

	@Override
	public void process(Map<String, ClassNode> classMap) {
		Map<String, ClassNode> map = new HashMap<>();
		for (ClassNode cn : classMap.values()) {
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			cn.accept(writer);
			ClassNode clone = new ClassNode();
			new ClassReader(writer.toByteArray()).accept(clone, ClassReader.SKIP_DEBUG);
			map.put(clone.name, clone);
		}
		classMap.clear();
		classMap.putAll(map);
	}

}
