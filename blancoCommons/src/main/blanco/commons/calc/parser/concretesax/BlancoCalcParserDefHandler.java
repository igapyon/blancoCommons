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
package blanco.commons.calc.parser.concretesax;

import java.io.CharArrayWriter;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * BlancoCalcParserDefHandler��ۉ�SAX�N���X<br>
 * ���̃N���X�͉�͗pXML�t�@�C������͂Ƃ��ċ�ۉ�SAX�N���X�Ƃ��Đ�������܂����B<br>
 * ���̃\�[�X�R�[�h��blancoIg�ɂ��@�B�I�Ɏ�����������Ă��܂��B<br>
 * �T�^�I�ȗ��p���@�͉��L�̂悤�ɂȂ�܂��B<br>
 * 
 * <pre>
 * TransformerFactory tf = TransformerFactory.newInstance();
 * Transformer transformer = tf.newTransformer();
 * transformer.transform(new StreamSource(inStream), new SAXResult(
 *         new BlancoCalcParserDefHandler())); // ���ۂɂ͎����N���X��Handler��^���܂��B
 * </pre>
 */
public abstract class BlancoCalcParserDefHandler implements ContentHandler {
    /**
     * �G�������g�̃X�^�b�N�ł�<br>
     * XML�̊K�w�\�����L�����܂��B
     */
    private Stack<java.lang.String> _elementStack = new Stack<java.lang.String>();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerBlanco = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerTarget = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerBlancocalcparser = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerPropertyblock = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerStartstring = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerPropertykey = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerValue = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerValuemapping = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerResult = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerSource = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerTableblock = new CharArrayWriter();

    /**
     * characters�ɓn�����߂̃L���b�V���ł�<br>
     * ������f�[�^��~�ς��܂��B
     */
    private CharArrayWriter _writerTablecolumn = new CharArrayWriter();

    /**
     * startElement���ړ����t���̏C����[blanco]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param attrVersion
     *            �A�g���r���[�g[version]�̒l���n����܂��B
     */
    public abstract void startElementBlanco(String uri, String localName,
            String qName, String attrVersion) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[blanco]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementBlanco(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[blanco]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersBlanco(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[blanco]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceBlanco(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[target]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param attrName
     *            �A�g���r���[�g[name]�̒l���n����܂��B
     */
    public abstract void startElementTarget(String uri, String localName,
            String qName, String attrName) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[target]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementTarget(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[target]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersTarget(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[target]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceTarget(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[blancocalcparser]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param attrName
     *            �A�g���r���[�g[name]�̒l���n����܂��B
     */
    public abstract void startElementBlancocalcparser(String uri,
            String localName, String qName, String attrName)
            throws SAXException;

    /**
     * endElement���ړ����t���̏C����[blancocalcparser]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementBlancocalcparser(String uri,
            String localName, String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[blancocalcparser]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersBlancocalcparser(char[] ch, int start,
            int length) throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[blancocalcparser]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceBlancocalcparser(char[] ch,
            int start, int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[propertyblock]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param attrName
     *            �A�g���r���[�g[name]�̒l���n����܂��B
     * @param attrWaitY
     *            �A�g���r���[�g[waitY]�̒l���n����܂��B
     */
    public abstract void startElementPropertyblock(String uri,
            String localName, String qName, String attrName, String attrWaitY)
            throws SAXException;

    /**
     * endElement���ړ����t���̏C����[propertyblock]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementPropertyblock(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[propertyblock]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersPropertyblock(char[] ch, int start,
            int length) throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[propertyblock]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespacePropertyblock(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[startstring]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void startElementStartstring(String uri, String localName,
            String qName) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[startstring]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementStartstring(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[startstring]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersStartstring(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[startstring]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceStartstring(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[propertykey]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param attrName
     *            �A�g���r���[�g[name]�̒l���n����܂��B
     * @param attrWaitX
     *            �A�g���r���[�g[waitX]�̒l���n����܂��B
     */
    public abstract void startElementPropertykey(String uri, String localName,
            String qName, String attrName, String attrWaitX)
            throws SAXException;

    /**
     * endElement���ړ����t���̏C����[propertykey]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementPropertykey(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[propertykey]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersPropertykey(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[propertykey]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespacePropertykey(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[value]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void startElementValue(String uri, String localName,
            String qName) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[value]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementValue(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[value]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersValue(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[value]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceValue(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[valuemapping]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void startElementValuemapping(String uri, String localName,
            String qName) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[valuemapping]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementValuemapping(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[valuemapping]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersValuemapping(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[valuemapping]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceValuemapping(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[result]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void startElementResult(String uri, String localName,
            String qName) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[result]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementResult(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[result]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersResult(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[result]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceResult(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[source]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void startElementSource(String uri, String localName,
            String qName) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[source]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementSource(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[source]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersSource(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[source]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceSource(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[tableblock]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param attrName
     *            �A�g���r���[�g[name]�̒l���n����܂��B
     * @param attrWaitY
     *            �A�g���r���[�g[waitY]�̒l���n����܂��B
     * @param attrTitleheight
     *            �A�g���r���[�g[titleheight]�̒l���n����܂��B
     * @param attrRowname
     *            �A�g���r���[�g[rowname]�̒l���n����܂��B
     */
    public abstract void startElementTableblock(String uri, String localName,
            String qName, String attrName, String attrWaitY,
            String attrTitleheight, String attrRowname) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[tableblock]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementTableblock(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[tableblock]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersTableblock(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[tableblock]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceTableblock(char[] ch, int start,
            int length) throws SAXException;

    /**
     * startElement���ړ����t���̏C����[tablecolumn]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param attrName
     *            �A�g���r���[�g[name]�̒l���n����܂��B
     */
    public abstract void startElementTablecolumn(String uri, String localName,
            String qName, String attrName) throws SAXException;

    /**
     * endElement���ړ����t���̏C����[tablecolumn]�ŌĂяo����܂����B<br>
     * ���ړ����t���̏C�����̓��\�b�h���Ɋ܂܂����̂Ɠ����̂��̂��^�����܂��B
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public abstract void endElementTablecolumn(String uri, String localName,
            String qName) throws SAXException;

    /**
     * characters���ړ����t���̏C����[tablecolumn]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void charactersTablecolumn(char[] ch, int start, int length)
            throws SAXException;

    /**
     * ignorableWhitespace���ړ����t���̏C����[tablecolumn]�ŌĂяo����܂����B<br>
     * ���Ƃ�characters���\�b�h���W�񂵂���Ń��\�b�h���Ăяo����܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public abstract void ignorableWhitespaceTablecolumn(char[] ch, int start,
            int length) throws SAXException;

    /**
     * �I���W�i����startElement���Ăяo���ꂽ�̂ŁA��ۃ��\�b�h�ɌĂт킯�܂�
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     * @param atts
     *            �A�g���r���[�g�̃��X�g
     */
    public final void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        if (_elementStack.empty() == false) {
            final String previousElementOnStack = (String) _elementStack.peek();
            if (previousElementOnStack.equals("source")) {
                _writerSource.flush();
                char[] wrk = _writerSource.toCharArray();
                _writerSource.reset();
                if (wrk.length > 0) {
                    charactersSource(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("blanco")) {
                _writerBlanco.flush();
                char[] wrk = _writerBlanco.toCharArray();
                _writerBlanco.reset();
                if (wrk.length > 0) {
                    charactersBlanco(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("propertyblock")) {
                _writerPropertyblock.flush();
                char[] wrk = _writerPropertyblock.toCharArray();
                _writerPropertyblock.reset();
                if (wrk.length > 0) {
                    charactersPropertyblock(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("propertykey")) {
                _writerPropertykey.flush();
                char[] wrk = _writerPropertykey.toCharArray();
                _writerPropertykey.reset();
                if (wrk.length > 0) {
                    charactersPropertykey(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("startstring")) {
                _writerStartstring.flush();
                char[] wrk = _writerStartstring.toCharArray();
                _writerStartstring.reset();
                if (wrk.length > 0) {
                    charactersStartstring(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("tablecolumn")) {
                _writerTablecolumn.flush();
                char[] wrk = _writerTablecolumn.toCharArray();
                _writerTablecolumn.reset();
                if (wrk.length > 0) {
                    charactersTablecolumn(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("target")) {
                _writerTarget.flush();
                char[] wrk = _writerTarget.toCharArray();
                _writerTarget.reset();
                if (wrk.length > 0) {
                    charactersTarget(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("blancocalcparser")) {
                _writerBlancocalcparser.flush();
                char[] wrk = _writerBlancocalcparser.toCharArray();
                _writerBlancocalcparser.reset();
                if (wrk.length > 0) {
                    charactersBlancocalcparser(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("value")) {
                _writerValue.flush();
                char[] wrk = _writerValue.toCharArray();
                _writerValue.reset();
                if (wrk.length > 0) {
                    charactersValue(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("valuemapping")) {
                _writerValuemapping.flush();
                char[] wrk = _writerValuemapping.toCharArray();
                _writerValuemapping.reset();
                if (wrk.length > 0) {
                    charactersValuemapping(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("tableblock")) {
                _writerTableblock.flush();
                char[] wrk = _writerTableblock.toCharArray();
                _writerTableblock.reset();
                if (wrk.length > 0) {
                    charactersTableblock(wrk, 0, wrk.length);
                }

            } else if (previousElementOnStack.equals("result")) {
                _writerResult.flush();
                char[] wrk = _writerResult.toCharArray();
                _writerResult.reset();
                if (wrk.length > 0) {
                    charactersResult(wrk, 0, wrk.length);
                }

            }
        }
        _elementStack.push(qName);
        if (qName.equals("source")) {
            startElementSource(uri, localName, qName);
        } else if (qName.equals("blanco")) {
            startElementBlanco(uri, localName, qName, atts.getValue("version"));
        } else if (qName.equals("propertyblock")) {
            startElementPropertyblock(uri, localName, qName, atts
                    .getValue("name"), atts.getValue("waitY"));
        } else if (qName.equals("propertykey")) {
            startElementPropertykey(uri, localName, qName, atts
                    .getValue("name"), atts.getValue("waitX"));
        } else if (qName.equals("startstring")) {
            startElementStartstring(uri, localName, qName);
        } else if (qName.equals("tablecolumn")) {
            startElementTablecolumn(uri, localName, qName, atts
                    .getValue("name"));
        } else if (qName.equals("target")) {
            startElementTarget(uri, localName, qName, atts.getValue("name"));
        } else if (qName.equals("blancocalcparser")) {
            startElementBlancocalcparser(uri, localName, qName, atts
                    .getValue("name"));
        } else if (qName.equals("value")) {
            startElementValue(uri, localName, qName);
        } else if (qName.equals("valuemapping")) {
            startElementValuemapping(uri, localName, qName);
        } else if (qName.equals("tableblock")) {
            startElementTableblock(uri, localName, qName,
                    atts.getValue("name"), atts.getValue("waitY"), atts
                            .getValue("titleheight"), atts.getValue("rowname"));
        } else if (qName.equals("result")) {
            startElementResult(uri, localName, qName);
        }
    }

    /**
     * �I���W�i����endElement���Ăяo���ꂽ�̂ŁA��ۃ��\�b�h�ɌĂт킯�܂�
     * 
     * @param uri
     *            ���O���URI
     * @param localName
     *            ���[�J����
     * @param qName
     *            �ړ����t���̏C����
     */
    public final void endElement(String uri, String localName, String qName)
            throws SAXException {
        final String currentElementOnStack = (String) _elementStack.peek();
        if (currentElementOnStack.equals(qName) == false) {
            throw new SAXException("XML�ُ�B���҂���G�������g[" + currentElementOnStack
                    + "]�Ɛړ����t���̏C����[" + qName + "]�Ƃ�����Ă��܂�.");
        }
        if (qName.equals("source")) {
            _writerSource.flush();
            char[] wrk = _writerSource.toCharArray();
            _writerSource.reset();
            if (wrk.length > 0) {
                charactersSource(wrk, 0, wrk.length);
            }
            endElementSource(uri, localName, qName);
        } else if (qName.equals("blanco")) {
            _writerBlanco.flush();
            char[] wrk = _writerBlanco.toCharArray();
            _writerBlanco.reset();
            if (wrk.length > 0) {
                charactersBlanco(wrk, 0, wrk.length);
            }
            endElementBlanco(uri, localName, qName);
        } else if (qName.equals("propertyblock")) {
            _writerPropertyblock.flush();
            char[] wrk = _writerPropertyblock.toCharArray();
            _writerPropertyblock.reset();
            if (wrk.length > 0) {
                charactersPropertyblock(wrk, 0, wrk.length);
            }
            endElementPropertyblock(uri, localName, qName);
        } else if (qName.equals("propertykey")) {
            _writerPropertykey.flush();
            char[] wrk = _writerPropertykey.toCharArray();
            _writerPropertykey.reset();
            if (wrk.length > 0) {
                charactersPropertykey(wrk, 0, wrk.length);
            }
            endElementPropertykey(uri, localName, qName);
        } else if (qName.equals("startstring")) {
            _writerStartstring.flush();
            char[] wrk = _writerStartstring.toCharArray();
            _writerStartstring.reset();
            if (wrk.length > 0) {
                charactersStartstring(wrk, 0, wrk.length);
            }
            endElementStartstring(uri, localName, qName);
        } else if (qName.equals("tablecolumn")) {
            _writerTablecolumn.flush();
            char[] wrk = _writerTablecolumn.toCharArray();
            _writerTablecolumn.reset();
            if (wrk.length > 0) {
                charactersTablecolumn(wrk, 0, wrk.length);
            }
            endElementTablecolumn(uri, localName, qName);
        } else if (qName.equals("target")) {
            _writerTarget.flush();
            char[] wrk = _writerTarget.toCharArray();
            _writerTarget.reset();
            if (wrk.length > 0) {
                charactersTarget(wrk, 0, wrk.length);
            }
            endElementTarget(uri, localName, qName);
        } else if (qName.equals("blancocalcparser")) {
            _writerBlancocalcparser.flush();
            char[] wrk = _writerBlancocalcparser.toCharArray();
            _writerBlancocalcparser.reset();
            if (wrk.length > 0) {
                charactersBlancocalcparser(wrk, 0, wrk.length);
            }
            endElementBlancocalcparser(uri, localName, qName);
        } else if (qName.equals("value")) {
            _writerValue.flush();
            char[] wrk = _writerValue.toCharArray();
            _writerValue.reset();
            if (wrk.length > 0) {
                charactersValue(wrk, 0, wrk.length);
            }
            endElementValue(uri, localName, qName);
        } else if (qName.equals("valuemapping")) {
            _writerValuemapping.flush();
            char[] wrk = _writerValuemapping.toCharArray();
            _writerValuemapping.reset();
            if (wrk.length > 0) {
                charactersValuemapping(wrk, 0, wrk.length);
            }
            endElementValuemapping(uri, localName, qName);
        } else if (qName.equals("tableblock")) {
            _writerTableblock.flush();
            char[] wrk = _writerTableblock.toCharArray();
            _writerTableblock.reset();
            if (wrk.length > 0) {
                charactersTableblock(wrk, 0, wrk.length);
            }
            endElementTableblock(uri, localName, qName);
        } else if (qName.equals("result")) {
            _writerResult.flush();
            char[] wrk = _writerResult.toCharArray();
            _writerResult.reset();
            if (wrk.length > 0) {
                charactersResult(wrk, 0, wrk.length);
            }
            endElementResult(uri, localName, qName);
        }
        // �Ō�Ƀ|�b�v���ĊK�w���ЂƂ߂��܂��B
        _elementStack.pop();
    }

    /**
     * �I���W�i����characters���Ăяo���ꂽ�̂ŁA��ۃ��\�b�h�ɌĂт킯�܂� �W�񂵂���ŌĂт킯���s���܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public final void characters(char[] ch, int start, int length)
            throws SAXException {
        final String currentElementOnStack = (String) _elementStack.peek();
        if (currentElementOnStack.equals("source")) {
            // charactersSource(ch, start, length);
            _writerSource.write(ch, start, length);
        } else if (currentElementOnStack.equals("blanco")) {
            // charactersBlanco(ch, start, length);
            _writerBlanco.write(ch, start, length);
        } else if (currentElementOnStack.equals("propertyblock")) {
            // charactersPropertyblock(ch, start, length);
            _writerPropertyblock.write(ch, start, length);
        } else if (currentElementOnStack.equals("propertykey")) {
            // charactersPropertykey(ch, start, length);
            _writerPropertykey.write(ch, start, length);
        } else if (currentElementOnStack.equals("startstring")) {
            // charactersStartstring(ch, start, length);
            _writerStartstring.write(ch, start, length);
        } else if (currentElementOnStack.equals("tablecolumn")) {
            // charactersTablecolumn(ch, start, length);
            _writerTablecolumn.write(ch, start, length);
        } else if (currentElementOnStack.equals("target")) {
            // charactersTarget(ch, start, length);
            _writerTarget.write(ch, start, length);
        } else if (currentElementOnStack.equals("blancocalcparser")) {
            // charactersBlancocalcparser(ch, start, length);
            _writerBlancocalcparser.write(ch, start, length);
        } else if (currentElementOnStack.equals("value")) {
            // charactersValue(ch, start, length);
            _writerValue.write(ch, start, length);
        } else if (currentElementOnStack.equals("valuemapping")) {
            // charactersValuemapping(ch, start, length);
            _writerValuemapping.write(ch, start, length);
        } else if (currentElementOnStack.equals("tableblock")) {
            // charactersTableblock(ch, start, length);
            _writerTableblock.write(ch, start, length);
        } else if (currentElementOnStack.equals("result")) {
            // charactersResult(ch, start, length);
            _writerResult.write(ch, start, length);
        }
    }

    /**
     * �I���W�i����ignorableWhitespace���Ăяo���ꂽ�̂ŁA��ۃ��\�b�h�ɌĂт킯�܂� �W�񂵂���ŌĂт킯���s���܂��B
     * 
     * @param ch
     *            XML�����̕���
     * @param start
     *            �z����̊J�n�ʒu
     * @param length
     *            �z�񂩂�ǂݎ�镶����
     */
    public final void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        final String currentElementOnStack = (String) _elementStack.peek();
        if (currentElementOnStack.equals("source")) {
            ignorableWhitespaceSource(ch, start, length);
        } else if (currentElementOnStack.equals("blanco")) {
            ignorableWhitespaceBlanco(ch, start, length);
        } else if (currentElementOnStack.equals("propertyblock")) {
            ignorableWhitespacePropertyblock(ch, start, length);
        } else if (currentElementOnStack.equals("propertykey")) {
            ignorableWhitespacePropertykey(ch, start, length);
        } else if (currentElementOnStack.equals("startstring")) {
            ignorableWhitespaceStartstring(ch, start, length);
        } else if (currentElementOnStack.equals("tablecolumn")) {
            ignorableWhitespaceTablecolumn(ch, start, length);
        } else if (currentElementOnStack.equals("target")) {
            ignorableWhitespaceTarget(ch, start, length);
        } else if (currentElementOnStack.equals("blancocalcparser")) {
            ignorableWhitespaceBlancocalcparser(ch, start, length);
        } else if (currentElementOnStack.equals("value")) {
            ignorableWhitespaceValue(ch, start, length);
        } else if (currentElementOnStack.equals("valuemapping")) {
            ignorableWhitespaceValuemapping(ch, start, length);
        } else if (currentElementOnStack.equals("tableblock")) {
            ignorableWhitespaceTableblock(ch, start, length);
        } else if (currentElementOnStack.equals("result")) {
            ignorableWhitespaceResult(ch, start, length);
        }
    }

    /** ���̃��\�b�h�𖳎����邽�߂̃��\�b�h�ł��B */
    public void setDocumentLocator(Locator locator) {
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
    }

    public void skippedEntity(String name) throws SAXException {
    }
}
