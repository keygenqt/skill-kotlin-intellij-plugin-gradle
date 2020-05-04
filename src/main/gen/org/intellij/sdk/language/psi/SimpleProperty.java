// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.keygenqt.plugin.simple.contributor.SimpleNamedElement;
import com.intellij.navigation.ItemPresentation;

public interface SimpleProperty extends SimpleNamedElement {

  @Nullable
  String getKey();

  @Nullable
  String getValue();

  @Nullable
  String getName();

  @Nullable
  PsiElement setName(@Nullable String newName);

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  ItemPresentation getPresentation();

}
