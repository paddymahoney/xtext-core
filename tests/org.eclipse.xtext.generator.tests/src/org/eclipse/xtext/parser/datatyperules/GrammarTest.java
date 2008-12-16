/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.parser.datatyperules;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parser.datatyperules.services.DatatypeRulesTestLanguageGrammarAccess;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class GrammarTest extends TestCase {
	
	private IGrammarAccess grammarAccess;
	private Grammar grammar;

	@Override
	protected void setUp() throws Exception {
		DatatypeRulesTestLanguageStandaloneSetup.doSetup();
		grammarAccess = DatatypeRulesTestLanguageGrammarAccess.INSTANCE;
		grammar = grammarAccess.getGrammar();
	}
	
	public void testGrammarAccess() {
		assertEquals(2, grammar.getMetamodelDeclarations().size());
		assertEquals(5, grammar.getRules().size());
	}
	
	public void testRuleCompositeModel() {
		AbstractRule rule = grammar.getRules().get(0);
		assertEquals("CompositeModel", rule.getName());
		ParserRule parserRule = (ParserRule) rule;
		assertFalse(GrammarUtil.isDatatypeRule(parserRule));
		assertNotNull(rule.getType());
		assertNotNull(rule.getType().getType());
		assertEquals("CompositeModel", rule.getType().getType().getName());
	}
	
	public void testRuleModel() {
		AbstractRule rule = grammar.getRules().get(1);
		assertEquals("Model", rule.getName());
		ParserRule parserRule = (ParserRule) rule;
		assertFalse(GrammarUtil.isDatatypeRule(parserRule));
		assertNotNull(rule.getType());
		assertNotNull(rule.getType().getType());
		assertEquals("Model", rule.getType().getType().getName());
	}
	
	public void testRuleModelId() {
		AbstractRule rule = grammar.getRules().get(2);
		assertEquals("ModelId", rule.getName());
		ParserRule parserRule = (ParserRule) rule;
		assertTrue(GrammarUtil.isDatatypeRule(parserRule));
		assertNotNull(rule.getType());
		assertNotNull(rule.getType().getType());
		assertEquals(EcorePackage.Literals.ESTRING, rule.getType().getType());
	}
	
	public void testRuleNestedModelId() {
		AbstractRule rule = grammar.getRules().get(3);
		assertEquals("NestedModelId", rule.getName());
		ParserRule parserRule = (ParserRule) rule;
		assertTrue(GrammarUtil.isDatatypeRule(parserRule));
		assertNotNull(rule.getType());
		assertNotNull(rule.getType().getType());
		assertEquals(EcorePackage.Literals.ESTRING, rule.getType().getType());
	}
	
	public void testRuleFraction() {
		AbstractRule rule = grammar.getRules().get(4);
		assertEquals("Fraction", rule.getName());
		ParserRule parserRule = (ParserRule) rule;
		assertTrue(GrammarUtil.isDatatypeRule(parserRule));
		assertNotNull(rule.getType());
		assertNotNull(rule.getType().getType());
		assertEquals(EcorePackage.Literals.EBIG_DECIMAL, rule.getType().getType());
	}

}
