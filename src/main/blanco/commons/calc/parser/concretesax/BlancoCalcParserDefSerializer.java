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

import java.io.OutputStream;
import java.io.Writer;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

;

/**
 * BlancoCalcParserDefSerializer��ۉ�SAX�N���X<br>
 * SAX�𗘗p����XML�����o�������b�s���O����N���X�ł��B<br>
 * ���̃N���X�͉�͗pXML�t�@�C������͂Ƃ��ċ�ۉ�SAX�N���X�Ƃ��Đ�������܂����B<br>
 * ���̃\�[�X�R�[�h��blancoIg�ɂ��@�B�I�Ɏ�����������Ă��܂��B ���̃N���X�� implements ContentHandler �͍s���܂���B<br>
 * �T�^�I�ȗ��p���@�͉��L�̂悤�ɂȂ�܂��B<br>
 * 
 * <pre>
 * BlancoCalcParserDefSerializer serializer = new BlancoCalcParserDefSerializer(
 *         outStream);
 * serializer.startDocument();
 * serializer.startElementXXXX();
 * serializer.characters(&quot;������������������&quot;);
 * serializer.endElementXXXX();
 * serializer.endDocument();
 * </pre>
 */
public class BlancoCalcParserDefSerializer {
    /**
     * �����I�ɗ��p����o�͗pSAX�n���h��<br>
     * �A����̃X�g���[���͊O����close�����K�v������܂��B
     */
    private TransformerHandler _saxHandler;

    public BlancoCalcParserDefSerializer(OutputStream outStream)
            throws TransformerConfigurationException {
        TransformerFactory tf = TransformerFactory.newInstance();
        SAXTransformerFactory saxTf = (SAXTransformerFactory) tf;
        _saxHandler = saxTf.newTransformerHandler();
        _saxHandler.setResult(new StreamResult(outStream));
    }

    public BlancoCalcParserDefSerializer(Writer writer)
            throws TransformerConfigurationException {
        TransformerFactory tf = TransformerFactory.newInstance();
        SAXTransformerFactory saxTf = (SAXTransformerFactory) tf;
        _saxHandler = saxTf.newTransformerHandler();
        _saxHandler.setResult(new StreamResult(writer));
    }

    /**
     * startDocument�𔭐������܂��B
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void startDocument() throws SAXException {
        _saxHandler.startDocument();
    }

    /**
     * endDocument�𔭐������܂��B
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void endDocument() throws SAXException {
        _saxHandler.endDocument();
    }

    /** startPrefixMapping�𔭐������܂��B */
    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
        _saxHandler.startPrefixMapping(prefix, uri);
    }

    /**
     * endPrefixMapping�𔭐������܂��B
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void endPrefixMapping(String prefix) throws SAXException {
        _saxHandler.endPrefixMapping(prefix);
    }

    /**
     * setDocumentLocator�𔭐������܂��B�B
     * 
     * @param locator
     *            ���P�[�^
     */
    public void setDocumentLocator(Locator locator) {
        _saxHandler.setDocumentLocator(locator);
    }

    /**
     * processingInstruction�𔭐������܂��B
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void processingInstruction(String target, String data)
            throws SAXException {
        _saxHandler.processingInstruction(target, data);
    }

    /**
     * skippedEntity�𔭐������܂��B
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void skippedEntity(String name) throws SAXException {
        _saxHandler.skippedEntity(name);
    }

    /**
     * characters���\�b�h���Ăяo���܂��B<br>
     * 
     * @param ch
     *            �o�͂�����������
     * @param start
     *            �J�n�ʒu
     * @param length
     *            ������̒���
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        _saxHandler.characters(ch, start, length);
    }

    /**
     * characters���\�b�h���Ăяo���܂��B<br>
     * ���ȈՉ��̂��߂� ������ java.lang.String���������̂ł��B
     * 
     * @param data
     *            �o�͂�����������
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void characters(String data) throws SAXException {
        final char[] chars = data.toCharArray();
        _saxHandler.characters(chars, 0, chars.length);
    }

    /**
     * ignorableWhitespace�𔭐������܂��B
     * 
     * @throws SAXException
     *             SAX�֘A�̗�O�����������ꍇ�B
     */
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        _saxHandler.ignorableWhitespace(ch, start, length);
    }

    /**
     * startElement��ړ����t���̏C����[blanco]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [blanco]<br>
     * �ړ����t���̏C����[blanco]<br>
     * 
     * @param attrVersion
     *            �A�g���r���[�g[version]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     */
    public void startElementBlanco(String attrVersion) throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        if (attrVersion != null) {
            attributes.addAttribute("", "version", "version", "CDATA",
                    attrVersion);
        }
        _saxHandler.startElement("", "blanco", "blanco", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[blanco]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [blanco]<br>
     * �ړ����t���̏C����[blanco]<br>
     */
    public void endElementBlanco() throws SAXException {
        _saxHandler.endElement("", "blanco", "blanco");
    }

    /**
     * startElement��ړ����t���̏C����[target]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [target]<br>
     * �ړ����t���̏C����[target]<br>
     * 
     * @param attrName
     *            �A�g���r���[�g[name]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     */
    public void startElementTarget(String attrName) throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        if (attrName != null) {
            attributes.addAttribute("", "name", "name", "CDATA", attrName);
        }
        _saxHandler.startElement("", "target", "target", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[target]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [target]<br>
     * �ړ����t���̏C����[target]<br>
     */
    public void endElementTarget() throws SAXException {
        _saxHandler.endElement("", "target", "target");
    }

    /**
     * startElement��ړ����t���̏C����[blancocalcparser]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [blancocalcparser]<br>
     * �ړ����t���̏C����[blancocalcparser]<br>
     * 
     * @param attrName
     *            �A�g���r���[�g[name]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     */
    public void startElementBlancocalcparser(String attrName)
            throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        if (attrName != null) {
            attributes.addAttribute("", "name", "name", "CDATA", attrName);
        }
        _saxHandler.startElement("", "blancocalcparser", "blancocalcparser",
                attributes);
    }

    /**
     * endElement��ړ����t���̏C����[blancocalcparser]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [blancocalcparser]<br>
     * �ړ����t���̏C����[blancocalcparser]<br>
     */
    public void endElementBlancocalcparser() throws SAXException {
        _saxHandler.endElement("", "blancocalcparser", "blancocalcparser");
    }

    /**
     * startElement��ړ����t���̏C����[propertyblock]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [propertyblock]<br>
     * �ړ����t���̏C����[propertyblock]<br>
     * 
     * @param attrName
     *            �A�g���r���[�g[name]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     * @param attrWaitY
     *            �A�g���r���[�g[waitY]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     */
    public void startElementPropertyblock(String attrName, String attrWaitY)
            throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        if (attrName != null) {
            attributes.addAttribute("", "name", "name", "CDATA", attrName);
        }
        if (attrWaitY != null) {
            attributes.addAttribute("", "waitY", "waitY", "CDATA", attrWaitY);
        }
        _saxHandler.startElement("", "propertyblock", "propertyblock",
                attributes);
    }

    /**
     * endElement��ړ����t���̏C����[propertyblock]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [propertyblock]<br>
     * �ړ����t���̏C����[propertyblock]<br>
     */
    public void endElementPropertyblock() throws SAXException {
        _saxHandler.endElement("", "propertyblock", "propertyblock");
    }

    /**
     * startElement��ړ����t���̏C����[startstring]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [startstring]<br>
     * �ړ����t���̏C����[startstring]<br>
     */
    public void startElementStartstring() throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        _saxHandler.startElement("", "startstring", "startstring", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[startstring]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [startstring]<br>
     * �ړ����t���̏C����[startstring]<br>
     */
    public void endElementStartstring() throws SAXException {
        _saxHandler.endElement("", "startstring", "startstring");
    }

    /**
     * startElement��ړ����t���̏C����[propertykey]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [propertykey]<br>
     * �ړ����t���̏C����[propertykey]<br>
     * 
     * @param attrName
     *            �A�g���r���[�g[name]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     * @param attrWaitX
     *            �A�g���r���[�g[waitX]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     */
    public void startElementPropertykey(String attrName, String attrWaitX)
            throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        if (attrName != null) {
            attributes.addAttribute("", "name", "name", "CDATA", attrName);
        }
        if (attrWaitX != null) {
            attributes.addAttribute("", "waitX", "waitX", "CDATA", attrWaitX);
        }
        _saxHandler.startElement("", "propertykey", "propertykey", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[propertykey]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [propertykey]<br>
     * �ړ����t���̏C����[propertykey]<br>
     */
    public void endElementPropertykey() throws SAXException {
        _saxHandler.endElement("", "propertykey", "propertykey");
    }

    /**
     * startElement��ړ����t���̏C����[value]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [value]<br>
     * �ړ����t���̏C����[value]<br>
     */
    public void startElementValue() throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        _saxHandler.startElement("", "value", "value", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[value]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [value]<br>
     * �ړ����t���̏C����[value]<br>
     */
    public void endElementValue() throws SAXException {
        _saxHandler.endElement("", "value", "value");
    }

    /**
     * startElement��ړ����t���̏C����[valuemapping]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [valuemapping]<br>
     * �ړ����t���̏C����[valuemapping]<br>
     */
    public void startElementValuemapping() throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        _saxHandler
                .startElement("", "valuemapping", "valuemapping", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[valuemapping]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [valuemapping]<br>
     * �ړ����t���̏C����[valuemapping]<br>
     */
    public void endElementValuemapping() throws SAXException {
        _saxHandler.endElement("", "valuemapping", "valuemapping");
    }

    /**
     * startElement��ړ����t���̏C����[result]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [result]<br>
     * �ړ����t���̏C����[result]<br>
     */
    public void startElementResult() throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        _saxHandler.startElement("", "result", "result", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[result]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [result]<br>
     * �ړ����t���̏C����[result]<br>
     */
    public void endElementResult() throws SAXException {
        _saxHandler.endElement("", "result", "result");
    }

    /**
     * startElement��ړ����t���̏C����[source]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [source]<br>
     * �ړ����t���̏C����[source]<br>
     */
    public void startElementSource() throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        _saxHandler.startElement("", "source", "source", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[source]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [source]<br>
     * �ړ����t���̏C����[source]<br>
     */
    public void endElementSource() throws SAXException {
        _saxHandler.endElement("", "source", "source");
    }

    /**
     * startElement��ړ����t���̏C����[tableblock]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [tableblock]<br>
     * �ړ����t���̏C����[tableblock]<br>
     * 
     * @param attrName
     *            �A�g���r���[�g[name]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     * @param attrWaitY
     *            �A�g���r���[�g[waitY]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     * @param attrTitleheight
     *            �A�g���r���[�g[titleheight]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ�
     *            null���Z�b�g���Ă��������B
     * @param attrRowname
     *            �A�g���r���[�g[rowname]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     */
    public void startElementTableblock(String attrName, String attrWaitY,
            String attrTitleheight, String attrRowname) throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        if (attrName != null) {
            attributes.addAttribute("", "name", "name", "CDATA", attrName);
        }
        if (attrWaitY != null) {
            attributes.addAttribute("", "waitY", "waitY", "CDATA", attrWaitY);
        }
        if (attrTitleheight != null) {
            attributes.addAttribute("", "titleheight", "titleheight", "CDATA",
                    attrTitleheight);
        }
        if (attrRowname != null) {
            attributes.addAttribute("", "rowname", "rowname", "CDATA",
                    attrRowname);
        }
        _saxHandler.startElement("", "tableblock", "tableblock", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[tableblock]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [tableblock]<br>
     * �ړ����t���̏C����[tableblock]<br>
     */
    public void endElementTableblock() throws SAXException {
        _saxHandler.endElement("", "tableblock", "tableblock");
    }

    /**
     * startElement��ړ����t���̏C����[tablecolumn]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [tablecolumn]<br>
     * �ړ����t���̏C����[tablecolumn]<br>
     * 
     * @param attrName
     *            �A�g���r���[�g[name]�̒l��n���܂��B�A�g���r���[�g���Z�b�g�������Ȃ��ꍇ�ɂ� null���Z�b�g���Ă��������B
     */
    public void startElementTablecolumn(String attrName) throws SAXException {
        AttributesImpl attributes = new AttributesImpl();
        if (attrName != null) {
            attributes.addAttribute("", "name", "name", "CDATA", attrName);
        }
        _saxHandler.startElement("", "tablecolumn", "tablecolumn", attributes);
    }

    /**
     * endElement��ړ����t���̏C����[tablecolumn]�Ƃ��ČĂяo���܂��B<br>
     * ����{�I�ȏ��̓��\�b�h�����Ɋ܂܂��̂ň�������͏�����Ă��܂��B<br>
     * ���O���URI []<br>
     * ���[�J���� [tablecolumn]<br>
     * �ړ����t���̏C����[tablecolumn]<br>
     */
    public void endElementTablecolumn() throws SAXException {
        _saxHandler.endElement("", "tablecolumn", "tablecolumn");
    }
}
