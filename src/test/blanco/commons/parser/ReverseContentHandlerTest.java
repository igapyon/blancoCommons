/*
 * blanco Framework
 * Copyright (C) 2004-2009 IGA Tosiki
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
/*******************************************************************************
 * Copyright (c) 2009 IGA Tosiki, NTT DATA BUSINESS BRAINS Corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IGA Tosiki (NTT DATA BUSINESS BRAINS Corp.) - initial API and implementation
 *******************************************************************************/
package blanco.commons.parser;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;

import junit.framework.TestCase;

import org.xml.sax.InputSource;

import blanco.commons.calc.parser.BlancoCalcParser;

/**
 * ReverseContentHandler�̒P�̎������s���܂��B
 * 
 * @author IGA Tosiki
 */
public class ReverseContentHandlerTest extends TestCase {

    /*
     * void parse �̃e�X�g���̃N���X(String)
     */
    public void testParseString() {
        try {
            StringWriter writer = new StringWriter();
            SAXResult result = new SAXResult(new ReverseContentHandler(writer));
            result.setLexicalHandler(new SystemOutLexicalHandler(result
                    .getLexicalHandler()));
            BlancoCalcParser.getTransformer().transform(
                    new SAXSource(new InputSource(new StringReader(
                            "<html><head></head><body></body></html>"))),
                    result);
            writer.flush();
            System.out.println(writer.toString());
        } catch (TransformerException ex) {
            System.out.println("XML�h�L�������g�ۑ����ɕϊ���O���������܂���.:" + ex.toString());
            ex.printStackTrace();
            return;
        }
    }
}
