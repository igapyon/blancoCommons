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
package blanco.commons.calc.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

import blanco.commons.parser.ContentHandlerStream;

/**
 * Calc��ǂݎ�邽�߂�SAX2�p�[�T�ł��B <br>
 * �\�v�Z�t�@�C���ǂݎ��̍ۂ̔ėp�I�Œ��ۓI��SAX2�n���h����񋟂��܂��B
 * 
 * @author IGA Tosiki
 */
public abstract class AbstractBlancoCalcParser implements XMLReader {
    /**
     * �R���e���c�n���h�����L�����܂��B
     */
    private ContentHandler contentHandler = null;

    private ContentHandlerStream chainedContentHandler = null;

    public static final String URI_PROPERTY_NAME_WORKBOOK = "http://blanco/commons/calc/parser/workbook";

    public static final String URI_PROPERTY_NAME_SHEET = "http://blanco/commons/calc/parser/sheet";

    private String _propertyNameWorkbook = "workbook";

    private String _propertyNameSheet = "sheet";

    /**
     * �t�B�[�`���[���擾���܂��B
     * 
     * @param arg0
     *            �t�B�[�`���[�B
     * @see org.xml.sax.XMLReader#getFeature(java.lang.String)
     */
    public final boolean getFeature(final String arg0)
            throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
    }

    /**
     * �t�B�[�`���[��ݒ肵�܂��B
     * 
     * @param name
     *            �t�B�[�`���[���B
     * @param value
     *            �t�B�[�`���[�̒l�B
     * @see org.xml.sax.XMLReader#setFeature(java.lang.String, boolean)
     */
    public final void setFeature(final String name, boolean value)
            throws SAXNotRecognizedException, SAXNotSupportedException {
    }

    /**
     * �v���p�e�B���擾���܂��B
     */
    public final Object getProperty(final String name)
            throws SAXNotRecognizedException, SAXNotSupportedException {

        if (name.equals(URI_PROPERTY_NAME_WORKBOOK)) {
            return _propertyNameWorkbook;
        } else if (name.equals(URI_PROPERTY_NAME_SHEET)) {
            return _propertyNameSheet;
        } else {
            throw new SAXNotRecognizedException("���̖��̂̓n���h�����O�ł��܂���." + name);
        }
    }

    /**
     * �v���p�e�B��ݒ肵�܂��B
     */
    public final void setProperty(final String name, final Object value)
            throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals(URI_PROPERTY_NAME_WORKBOOK)) {
            _propertyNameWorkbook = (String) value;
        } else if (name.equals(URI_PROPERTY_NAME_SHEET)) {
            _propertyNameSheet = (String) value;
        } else {
            throw new SAXNotRecognizedException("���̖��̂̓n���h�����O�ł��܂���." + name);
        }
    }

    /**
     * �G���e�B�e�B���]���o��ݒ肵�܂��B
     * 
     * @param arg0
     *            �G���e�B�e�B���]���o�̃I�u�W�F�N�g�B
     * @see org.xml.sax.XMLReader#setEntityResolver(org.xml.sax.EntityResolver)
     */
    public final void setEntityResolver(final EntityResolver arg0) {
    }

    /**
     * �G���e�B�e�B���]���o���擾���܂��B
     * 
     * @return �G���e�B�e�B���]���o�̃I�u�W�F�N�g�B
     * @see org.xml.sax.XMLReader#getEntityResolver()
     */
    public final EntityResolver getEntityResolver() {
        return null;
    }

    /**
     * DTD�n���h����ݒ肵�܂��B
     * 
     * @param arg0
     *            DTD�n���h���̃I�u�W�F�N�g�B
     * @see org.xml.sax.XMLReader#setDTDHandler(org.xml.sax.DTDHandler)
     */
    public final void setDTDHandler(final DTDHandler arg0) {
    }

    /**
     * DTD�n���h���̃I�u�W�F�N�g���擾���܂��B
     * 
     * @return DTD�n���h���̃I�u�W�F�N�g�B
     * @see org.xml.sax.XMLReader#getDTDHandler()
     */
    public final DTDHandler getDTDHandler() {
        return null;
    }

    /**
     * �R���e���c�n���h�����Z�b�g���܂��B
     */
    public final void setContentHandler(ContentHandler arg0) {
        contentHandler = arg0;
        if (chainedContentHandler != null) {
            chainedContentHandler.setContentHandler(arg0);
        }
    }

    /**
     * �R���e���c�n���h�����擾���܂��B
     */
    public final ContentHandler getContentHandler() {
        if (chainedContentHandler == null) {
            return contentHandler;
        } else {
            return chainedContentHandler;
        }
    }

    public final void chainContentHandlerStream(ContentHandlerStream arg0) {
        chainedContentHandler = arg0;
    }

    /**
     * �G���[�n���h���[��ݒ肵�܂��B
     * 
     * @param arg0
     *            �G���[�n���h���[�I�u�W�F�N�g�B
     * @see org.xml.sax.XMLReader#setErrorHandler(org.xml.sax.ErrorHandler)
     */
    public final void setErrorHandler(ErrorHandler arg0) {
    }

    /**
     * �G���[�n���h���[���擾���܂��B
     * 
     * @return �G���[�n���h���[�I�u�W�F�N�g�B
     * @see org.xml.sax.XMLReader#getErrorHandler()
     */
    public final ErrorHandler getErrorHandler() {
        return null;
    }

    /**
     * �p�[�X���s���܂��B
     * 
     * @param inputSource
     *            ��͑ΏۂƂȂ���̓\�[�X�B
     * @see org.xml.sax.XMLReader#parse(org.xml.sax.InputSource)
     */
    public final void parse(final InputSource inputSource) throws IOException,
            SAXException {
        Workbook workbook = null;
        final WorkbookSettings settings = new WorkbookSettings();
        // JExcelApi�̓���gc�Ăяo���𖳌������܂��B
        settings.setGCDisabled(true);
        // ���[�j���O�̕W���o�͂𖳌������܂��B
        settings.setSuppressWarnings(true);

        // 2006.09.29 t.iga
        // JExcelApi 2.6�ł́�u00??�G���A�̏����ɂ����āA�����I�Ɏw�肳�ꂽ�G���R�[�f�B���O�𗘗p���Ă��܂��B
        // �������A���̎d�l�� Excel�̋����Ƃ͈قȂ�܂��BExcel�ɍ��킹��� ISO8859_1 �Œ�̏������Ó��ƍl���܂��B
        // JExcelApi 2.6�̃f�t�H���g�ł́A����̃Z���� ��u00??�G���A�̕��� (��:�~(������)) ���������͂��ꂽ�ꍇ��
        // Excel�ǂݍ��ݎ��ɕ����������������܂��B
        settings.setEncoding("ISO8859_1");

        // ����ȊO�ɂ�locale�ݒ�Ȃǂ�����A�����̗]�n�͑��݂��邪�A����̎d�l�Ƃ��Ă͂����܂łƂ���B�B

        InputStream inStream = null;
        try {
            if (inputSource.getByteStream() != null) {
                // OK�ł��B���̂܂܏�����i�߂܂��B
            } else if (inputSource.getSystemId() != null
                    && inputSource.getSystemId().length() > 0) {
                inStream = new FileInputStream(inputSource.getSystemId());
                inputSource.setByteStream(inStream);
            } else {
                throw new IOException("�w�肳�ꂽInputSource�͏����ł��܂���.");
            }
            workbook = Workbook.getWorkbook(inputSource.getByteStream(),
                    settings);

            // ��������{���̃p�[�X���n�܂�܂��B
            parseWorkbook(workbook);
        } catch (BiffException e) {
            e.printStackTrace();
            throw new IOException("�\�����ʗ�O���������܂���.: " + e.toString());
        } finally {
            if (workbook != null) {
                workbook.close();
            }

            // InputSource�̃N���[�Y�͊O���ōs���܂��B
            // ���̒��ł� �����I�ɊJ�����X�g���[���̂ݏ������܂��B
            if (inStream != null) {
                inStream.close();
            }
        }
    }

    /**
     * �^����ꂽ�t�@�C�����p�[�X���܂��B
     * 
     * ��{�I�ɂ� inputSource�ł̃p�[�X�𐄏����܂��B
     * 
     * @param arg0
     *            �t�@�C���̃p�X�B
     */
    public final void parse(final String arg0) throws IOException, SAXException {
        InputSource inputSource = new InputSource(arg0);
        inputSource.setByteStream(new FileInputStream(arg0));
        parse(inputSource);
    }

    /**
     * ���[�N�u�b�N���p�[�X���܂�
     * 
     * @param workbook
     *            ���[�N�u�b�N
     * @throws SAXException
     *             SAX��O�����������ꍇ�B
     */
    private void parseWorkbook(final Workbook workbook) throws SAXException {
        getContentHandler().startDocument();
        getContentHandler().startElement("",
                (String) getProperty(URI_PROPERTY_NAME_WORKBOOK),
                (String) getProperty(URI_PROPERTY_NAME_WORKBOOK),
                new AttributesImpl());

        for (int indexSheet = 0; indexSheet < workbook.getNumberOfSheets(); indexSheet++) {
            Sheet sheet = workbook.getSheet(indexSheet);
            parseSheet(sheet);
        }
        getContentHandler().endElement("",
                (String) getProperty(URI_PROPERTY_NAME_WORKBOOK),
                (String) getProperty(URI_PROPERTY_NAME_WORKBOOK));
        getContentHandler().endDocument();
    }

    /**
     * �V�[�g���p�[�X���܂��B
     * 
     * @param sheet
     *            �V�[�g�I�u�W�F�N�g�B
     * @throws SAXException
     *             SAX��O�����������ꍇ�B
     */
    private final void parseSheet(final Sheet sheet) throws SAXException {
        // �V�[�g�̃G�������g�͏�ʃN���X�ŏ���
        AttributesImpl attrImpl = new AttributesImpl();
        attrImpl.addAttribute("", "name", "name", "CDATA", sheet.getName());
        getContentHandler().startElement("",
                (String) getProperty(URI_PROPERTY_NAME_SHEET),
                (String) getProperty(URI_PROPERTY_NAME_SHEET), attrImpl);

        startSheet(sheet.getName());

        int maxRows = sheet.getRows();

        for (int row = 0; row < maxRows; row++) {
            startRow(row + 1);
            Cell[] cells = sheet.getRow(row);
            for (int column = 0; column < cells.length; column++) {
                startColumn(column + 1);
                // �R���e���c��trim()�����ɁA���̂܂܂킽���܂��B
                String value = cells[column].getContents();
                fireCell(column + 1, row + 1, value);
                endColumn(column + 1);
            }
            endRow(row + 1);
        }

        endSheet(sheet);

        // �V�[�g�̃G�������g�͏�ʃN���X�ŏ���
        getContentHandler().endElement("",
                (String) getProperty(URI_PROPERTY_NAME_SHEET),
                (String) getProperty(URI_PROPERTY_NAME_SHEET));
    }

    /**
     * �V�[�g���J�n�����ۂɌĂяo����܂��B
     * 
     * @param sheetName
     *            �V�[�g���B
     * @throws SAXException
     *             SAX��O�����������ꍇ�B
     */
    protected abstract void startSheet(String sheetName) throws SAXException;

    /**
     * �V�[�g���I�������ۂɌĂяo����܂��B
     * 
     * @param sheet
     *            �V�[�g�I�u�W�F�N�g�B
     * @throws SAXException
     *             SAX��O�����������ꍇ�B
     */
    protected abstract void endSheet(final Sheet sheet) throws SAXException;

    protected abstract void startRow(int row) throws SAXException;

    protected abstract void endRow(int row) throws SAXException;

    protected abstract void startColumn(int column) throws SAXException;

    protected abstract void endColumn(int column) throws SAXException;

    /**
     * �Z��������ۂɌĂяo����܂��B
     * 
     * @param column
     * @param row
     * @param cellValue
     * @throws SAXException
     */
    protected abstract void fireCell(int column, int row, String cellValue)
            throws SAXException;

    /**
     * Transformer���擾���܂��B
     * 
     * @return �g�����X�t�H�[�܁[�I�u�W�F�N�g�B
     * @throws TransformerFactoryConfigurationError
     *             �g�����X�t�H�[�}�[�t�@�N�g���[�̃R���t�B�O���[�V�����̗�O�����������ꍇ�B
     * @throws TransformerConfigurationException
     *             �g�����X�t�H�[�}�[�R���t�B�O���[�V�����̗�O�����������ꍇ�B
     */
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
