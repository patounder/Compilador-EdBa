package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.cs.usfca.edu
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: CommonASTWithHiddenTokens.java,v 1.1 2007-10-19 15:55:01 soto-r Exp $
 */

import antlr.collections.AST;

/**
 * A CommonAST whose initialization copies hidden token information from the
 * Token used to create a node.
 */
public class CommonASTWithHiddenTokens extends CommonAST {
    protected CommonHiddenStreamToken hiddenBefore, hiddenAfter; // references

    // to hidden
    // tokens

    public CommonASTWithHiddenTokens() {
        super();
    }

    public CommonASTWithHiddenTokens(Token tok) {
        super(tok);
    }

    public CommonHiddenStreamToken getHiddenAfter() {
        return hiddenAfter;
    }

    public CommonHiddenStreamToken getHiddenBefore() {
        return hiddenBefore;
    }

    public void initialize(AST t) {
        hiddenBefore = ((CommonASTWithHiddenTokens) t).getHiddenBefore();
        hiddenAfter = ((CommonASTWithHiddenTokens) t).getHiddenAfter();
        super.initialize(t);
    }

    public void initialize(Token tok) {
        CommonHiddenStreamToken t = (CommonHiddenStreamToken) tok;
        super.initialize(t);
        hiddenBefore = t.getHiddenBefore();
        hiddenAfter = t.getHiddenAfter();
    }
}
