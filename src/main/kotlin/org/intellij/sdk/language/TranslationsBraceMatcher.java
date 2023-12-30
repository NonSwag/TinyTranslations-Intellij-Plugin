package org.intellij.sdk.language;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.intellij.sdk.language.psi.TranslationsTypes.*;

public class TranslationsBraceMatcher implements PairedBraceMatcher, DumbAware {

	private static final BracePair[] PAIRS = new BracePair[] {
			new BracePair(OPEN_TAG, CLOSE_TAG, true),
			new BracePair(TAG_OPEN, TAG_CLOSE, false),
			new BracePair(PH_OPEN, PH_CLOSE, false),
	};

	@Override
	public BracePair @NotNull [] getPairs() {
		return PAIRS;
	}

	@Override
	public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType iElementType, @Nullable IElementType iElementType1) {
		return true;
	}

	@Override
	public int getCodeConstructStart(@NotNull PsiFile psiFile, int i) {
		return 0;
	}
}
