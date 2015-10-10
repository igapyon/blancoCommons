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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * SAX�C�x���g����͂� ���ׂ��ׂɂ�����o�͂���\�[�X�R�[�h�𐶐����܂��B <br>
 * �Ƃ��Ă��s�v�c��SAX2�R���e���g�n���h���ł��B <br>
 * 2005.08.11 Tosiki Iga �V�K�쐬
 * 
 * @author IGA Tosiki
 */
public class ReverseContentHandler extends BufferedWriter implements
        ContentHandler {
    /**
     * ���o�[�X�R���e���g�n���h���̃C���X�^���X�𐶐����܂��B
     * 
     * @param writer
     *            ���o�[�X���ʂ̃\�[�X�R�[�h�̏o�͐�
     */
    public ReverseContentHandler(Writer writer) {
        super(writer);
    }

    public void setDocumentLocator(Locator arg0) {
        // �h�L�������g���P�[�^�͖������܂��B
    }

    public void startDocument() throws SAXException {
        try {
            write("TransformerFactory tf = TransformerFactory.newInstance();");
            newLine();
            write("SAXTransformerFactory saxTf = (SAXTransformerFactory) tf;");
            newLine();
            write("TransformerHandler handler = saxTf.newTransformerHandler();");
            newLine();
            write("handler.setResult(new StreamResult(response.getOutputStream()));");
            newLine();
            write("handler.startDocument();");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endDocument() throws SAXException {
        try {
            write("handler.endDocument();");
            newLine();
            flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startPrefixMapping(String arg0, String arg1)
            throws SAXException {
        try {
            write("handler.startPrefixMapping(\"" + arg0 + "\", \"" + arg1
                    + "\");");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void endPrefixMapping(String arg0) throws SAXException {
        try {
            write("handler.endPrefixMapping(\"" + arg0 + "\");");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ŏ��̃G�������g���ǂ����𔻒f���܂��B
     */
    private boolean isFirstAttributes = true;

    public void startElement(String uri, String localname, String qname,
            Attributes attributes) throws SAXException {
        try {
            if (isFirstAttributes) {
                isFirstAttributes = false;
                write("AttributesImpl attributes = new AttributesImpl();");
                newLine();
            } else {
                write("attributes = new AttributesImpl();");
                newLine();
            }
            for (int index = 0; index < attributes.getLength(); index++) {
                write("attributes.addAttribute(\"" + attributes.getURI(index)
                        + "\", \"" + attributes.getLocalName(index) + "\", \""
                        + attributes.getQName(index) + "\", \""
                        + attributes.getType(index) + "\", \""
                        + attributes.getValue(index) + "\");");
                newLine();
            }
            write("handler.startElement(\"" + uri + "\", \"" + localname
                    + "\", \"" + qname + "\", attributes);");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endElement(String arg0, String arg1, String arg2)
            throws SAXException {
        try {
            write("handler.endElement(\"" + arg0 + "\", \"" + arg1 + "\", \""
                    + arg2 + "\");");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
        try {
            // ��������0���Z�b�g���Ă���_�ɒ��ӁI
            write("handler.characters(\"" + new String(arg0, arg1, arg2)
                    + "\".toCharArray(), 0, " + arg2 + ");");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
            throws SAXException {
        try {
            write("handler.ignorableWhitespace(\""
                    + new String(arg0, arg1, arg2) + "\".toCharArray(), 0, "
                    + arg2 + ");");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processingInstruction(String arg0, String arg1)
            throws SAXException {
        try {
            write("handler.processingInstruction(\"" + arg0 + "\", \"" + arg1
                    + "\");");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void skippedEntity(String arg0) throws SAXException {
        try {
            write("handler.skippedEntity(\"" + arg0 + "\");");
            newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Transformer getTransformer()
            throws TransformerFactoryConfigurationError,
            TransformerConfigurationException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty("standalone", "yes");
        transformer.setOutputProperty("indent", "yes");
        return transformer;
    }
}