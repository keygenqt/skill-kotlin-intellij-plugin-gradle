// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.sdk.language.psi.SimpleTypes.*;
import com.keygenqt.plugin.simple.contributor.SimpleNamedElementImpl;
import org.intellij.sdk.language.psi.*;
import com.keygenqt.plugin.simple.psi.SimplePsiImplUtil;
import com.intellij.navigation.ItemPresentation;

public class SimplePropertyImpl extends SimpleNamedElementImpl implements SimpleProperty {

  public SimplePropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) accept((SimpleVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public String getKey() {
    return SimplePsiImplUtil.getKey(this);
  }

  @Override
  @Nullable
  public String getValue() {
    return SimplePsiImplUtil.getValue(this);
  }

  @Override
  @Nullable
  public String getName() {
    return SimplePsiImplUtil.getName(this);
  }

  @Override
  @Nullable
  public PsiElement setName(@Nullable String newName) {
    return SimplePsiImplUtil.setName(this, newName);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return SimplePsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @Nullable
  public ItemPresentation getPresentation() {
    return SimplePsiImplUtil.getPresentation(this);
  }

}
