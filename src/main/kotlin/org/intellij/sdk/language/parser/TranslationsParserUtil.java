package org.intellij.sdk.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import org.intellij.sdk.language.psi.TranslationsTypes;

import java.util.Stack;

public class TranslationsParserUtil extends GeneratedParserUtilBase {

	private static final Stack<String> keys = new Stack<>();

	public static boolean open(PsiBuilder b, int l) {
		String text = b.getTokenText();
		if (consumeToken(b, TranslationsTypes.TAG_KEY)) {
			keys.push(text);
			return true;
		}
		return false;
	}

	public static boolean close(PsiBuilder b, int l) {
		String name = b.getTokenText();
		if (consumeToken(b, TranslationsTypes.TAG_KEY)) {
			if (!keys.contains(name)) return false;
			String s = null;
			while (s == null || !s.equalsIgnoreCase(name)) {
				s = keys.pop();
			}
			return true;
		}
		return false;
	}
}
