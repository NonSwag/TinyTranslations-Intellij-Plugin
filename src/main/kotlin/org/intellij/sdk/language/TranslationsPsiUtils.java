package org.intellij.sdk.language;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.intellij.sdk.language.psi.TranslationsTypes;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TranslationsPsiUtils {

	public static void formatPsi(ASTNode root) {

		Queue<ASTNode> toProcess = new LinkedList<>(List.of(root.getChildren(TokenSet.ANY)));
		// root = root.copyElement();
		// root.removeRange(root.getFirstChildNode(), root.getLastChildNode());

		Stack<ASTNode> openTags = new Stack<>();
		openTags.push(root);

		while (!toProcess.isEmpty()) {
			ASTNode current = toProcess.poll();
			ASTNode stackTop = openTags.peek();
			if (current.getElementType().equals(TranslationsTypes.OPEN_TAG)) {
				stackTop.addChild(current);
				openTags.push(current);
				continue;
			}
			if (current.getElementType().equals(TranslationsTypes.CLOSE_TAG)) {
				if (stackTop.getElementType().equals(TranslationsTypes.OPEN_TAG)) {
					String key = stackTop.getChildren(TokenSet.ANY)[1].getText();
					if (key.equals(current.getChildren(TokenSet.ANY)[1].getText())) {
						openTags.pop().addChild(current);
					}
				}
				continue;
			}
			stackTop.addChild(current);
		}
	}
}
