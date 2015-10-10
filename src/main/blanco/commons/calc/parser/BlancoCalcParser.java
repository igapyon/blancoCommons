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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import jxl.Sheet;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import blanco.commons.calc.BlancoCalcUtil;
import blanco.commons.calc.parser.block.AbstractBlancoCalcParserBlock;
import blanco.commons.calc.parser.block.BlancoCalcParserPropertyBlock;
import blanco.commons.calc.parser.block.BlancoCalcParserPropertyKey;
import blanco.commons.calc.parser.block.BlancoCalcParserTableBlock;
import blanco.commons.calc.parser.block.BlancoCalcParserTableColumn;
import blanco.commons.calc.parser.block.BlancoCalcParserValueMapping;
import blanco.commons.calc.parser.concretesax.BlancoCalcParserDefHandler;

/**
 * Calc��ǂݎ�邽�߂�SAX2�p�[�T�ł��B <br>
 * �J�n������ƃG���e�B�e�B���Ƃ̊֘A�Â��Ȃǂ��������܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoCalcParser extends AbstractBlancoCalcParser {
    /**
     * �f�o�b�O���[�h�œ��삵�Ă��邩�ǂ����B
     */
    private static final boolean IS_DEBUG = false;

    /**
     * �����I�ɕێ����Ă���u���b�N
     */
    private List<AbstractBlancoCalcParserBlock> listBlock = new ArrayList<AbstractBlancoCalcParserBlock>();

    /**
     * �J�����g�u���b�N
     */
    private AbstractBlancoCalcParserBlock currentBlock = null;

    /**
     * �L�[�}�b�v�̍ۂ̃J�����g�A�C�e��
     */
    private BlancoCalcParserPropertyKey currentKeyMapItem = null;

    private int waitForValueX = -1;

    private int waitForValueY = -1;

    private int waitForIteratorTitleSearchY = -1;

    private boolean isNoCellExistOnRow = true;

    private boolean isFirstIteratorRowItem = true;

    /**
     * ���݂� BlancoCalcParser�̋N���G���g���|�C���g�ƂȂ��Ă��܂��B<br>
     * ��:
     * <code>BlancoCalcParser ./meta/BlancoCalcParserDef.xml ./meta/blancoCsvTemplate.xls ./output.xml</code>
     * 
     * @param args
     *            0�Ԗ�:�ݒ�t�@�C�� 1�Ԗ�:���̓t�@�C�� 2�Ԗ�:�o�̓t�@�C��
     */
    public static final void main(final String[] args) {
        if (args.length < 3) {
            System.out.println("usage: BlancoCalcParser �ݒ�t�@�C�� ���̓t�@�C�� �o�̓t�@�C��");
            return;
        }

        InputStream inStreamDef = null;
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStreamDef = new FileInputStream(args[0]);
            inStream = new BufferedInputStream(new FileInputStream(new File(
                    args[1])));
            outStream = new BufferedOutputStream(new FileOutputStream(new File(
                    args[2])));
            new BlancoCalcParser().process(inStreamDef, inStream, outStream);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            if (inStreamDef != null) {
                try {
                    inStreamDef.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * BlancoCalcParser�̃R���X�g���N�^�B
     */
    public BlancoCalcParser() {
    }

    /**
     * �ݒ�������Ƃ�Excel�t�@�C����XML�����܂��B
     * 
     * @param inStreamDef
     *            �ݒ���
     * @param inStreamCalc
     *            Excel���̓X�g���[��
     * @param outStreamXml
     *            XML�o�̓X�g���[��
     * @throws TransformerException
     */
    public void process(final InputStream inStreamDef,
            final InputStream inStreamCalc, final OutputStream outStreamXml)
            throws TransformerException {
        final BlancoCalcParser parser = new BlancoCalcParser();
        parser.readDef(inStreamDef);

        final SAXSource source = new SAXSource(parser, new InputSource(
                inStreamCalc));

        final TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(source, new StreamResult(outStreamXml));
    }

    /**
     * �w��̒�`�t�@�C����ǂݍ��݂܂�
     * 
     * @param inStreamDef
     *            ���̓X�g���[���B���̃X�g���[���͓����I�ɃN���[�Y���܂���B
     * @throws TransformerException
     */
    public void readDef(final InputStream inStreamDef)
            throws TransformerException {
        SAXResult result = new SAXResult(new BlancoCalcParserDefHandler() {

            /**
             * �u���b�N�̒��ۃN���X
             */
            private AbstractBlancoCalcParserBlock blockHeader = null;

            /**
             * �J�n��������L��
             */
            private List<java.lang.String> startStringList = new ArrayList<java.lang.String>();

            /**
             * PropertyKey�֘A
             */
            private BlancoCalcParserPropertyKey propKey = null;

            /**
             * Value�̔z����쐬���邽�߂̂���
             */
            private List<java.lang.String> valueList = new ArrayList<java.lang.String>();

            /**
             * ValuMapping�֘A
             */
            private List<BlancoCalcParserValueMapping> valueMappingList = new ArrayList<BlancoCalcParserValueMapping>();

            private List<java.lang.String> sourceList = new ArrayList<java.lang.String>();

            private String result = null;

            /**
             * TableColumn�֘A
             */
            private BlancoCalcParserTableColumn tableColumn = null;

            public void startDocument() throws SAXException {
            }

            public void endDocument() throws SAXException {
            }

            public void startElementBlanco(String uri, String localName,
                    String qName, String attrVersion) throws SAXException {
                // System.out.println("start: blanco: version[" + attrVersion
                // + "]");
            }

            public void endElementBlanco(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void charactersBlanco(char[] ch, int start, int length)
                    throws SAXException {
            }

            public void ignorableWhitespaceBlanco(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementTarget(String uri, String localName,
                    String qName, String attrName) throws SAXException {
            }

            public void endElementTarget(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void charactersTarget(char[] ch, int start, int length)
                    throws SAXException {
            }

            public void ignorableWhitespaceTarget(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementBlancocalcparser(String uri,
                    String localName, String qName, String attrName)
                    throws SAXException {
            }

            public void endElementBlancocalcparser(String uri,
                    String localName, String qName) throws SAXException {
            }

            public void charactersBlancocalcparser(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void ignorableWhitespaceBlancocalcparser(char[] ch,
                    int start, int length) throws SAXException {
            }

            public void startElementPropertyblock(String uri, String localName,
                    String qName, String attrName, String attrWaitY)
                    throws SAXException {
                // �u���b�N�̐V�K�쐬�B
                blockHeader = new BlancoCalcParserPropertyBlock(attrName);
                blockHeader.setSearchRangeY(Integer.parseInt(attrWaitY));

                add(blockHeader);
            }

            public void endElementPropertyblock(String uri, String localName,
                    String qName) throws SAXException {
                final String[] startString = new String[startStringList.size()];
                startStringList.toArray(startString);
                startStringList.clear();

                final BlancoCalcParserValueMapping valuemappings[] = new BlancoCalcParserValueMapping[valueMappingList
                        .size()];
                valueMappingList.toArray(valuemappings);
                valueMappingList.clear();

                // �u���b�N�̏I��
                blockHeader.setStartString(startString);
                blockHeader.setValueMapping(valuemappings);
            }

            public void charactersPropertyblock(char[] ch, int start, int length)
                    throws SAXException {
            }

            public void ignorableWhitespacePropertyblock(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementStartstring(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void endElementStartstring(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void charactersStartstring(char[] ch, int start, int length)
                    throws SAXException {
                startStringList.add(new String(ch, start, length));
            }

            public void ignorableWhitespaceStartstring(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementPropertykey(String uri, String localName,
                    String qName, String attrName, String attrWaitX)
                    throws SAXException {
                propKey = new BlancoCalcParserPropertyKey(attrName);
                propKey.setSearchRangeX(Integer.parseInt(attrWaitX));

                ((BlancoCalcParserPropertyBlock) blockHeader).add(propKey);
            }

            public void endElementPropertykey(String uri, String localName,
                    String qName) throws SAXException {
                final String[] values = new String[valueList.size()];
                valueList.toArray(values);
                valueList.clear();
                propKey.setKeyString(values);
            }

            public void charactersPropertykey(char[] ch, int start, int length)
                    throws SAXException {
            }

            public void ignorableWhitespacePropertykey(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementValue(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void endElementValue(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void charactersValue(char[] ch, int start, int length)
                    throws SAXException {
                valueList.add(new String(ch, start, length));
            }

            public void ignorableWhitespaceValue(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementValuemapping(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void endElementValuemapping(String uri, String localName,
                    String qName) throws SAXException {
                final String[] sources = new String[sourceList.size()];
                sourceList.toArray(sources);
                sourceList.clear();

                valueMappingList.add(new BlancoCalcParserValueMapping(sources,
                        result));
                result = null;
            }

            public void charactersValuemapping(char[] ch, int start, int length)
                    throws SAXException {
            }

            public void ignorableWhitespaceValuemapping(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementResult(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void endElementResult(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void charactersResult(char[] ch, int start, int length)
                    throws SAXException {
                result = new String(ch, start, length);
            }

            public void ignorableWhitespaceResult(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementSource(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void endElementSource(String uri, String localName,
                    String qName) throws SAXException {
            }

            public void charactersSource(char[] ch, int start, int length)
                    throws SAXException {
                sourceList.add(new String(ch, start, length));
            }

            public void ignorableWhitespaceSource(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementTableblock(String uri, String localName,
                    String qName, String attrName, String attrWaitY,
                    String attrTitleheight, String attrRowname)
                    throws SAXException {

                // �u���b�N�̐V�K�쐬�B
                blockHeader = new BlancoCalcParserTableBlock(attrName);
                blockHeader.setSearchRangeY(Integer.parseInt(attrWaitY));
                ((BlancoCalcParserTableBlock) blockHeader)
                        .setSearchRangeForTitleY(Integer
                                .parseInt(attrTitleheight));
                ((BlancoCalcParserTableBlock) blockHeader)
                        .setRowName(attrRowname);
                add(blockHeader);
            }

            public void endElementTableblock(String uri, String localName,
                    String qName) throws SAXException {
                final String[] startString = new String[startStringList.size()];
                startStringList.toArray(startString);
                startStringList.clear();

                final BlancoCalcParserValueMapping valuemappings[] = new BlancoCalcParserValueMapping[valueMappingList
                        .size()];
                valueMappingList.toArray(valuemappings);
                valueMappingList.clear();

                // �u���b�N�̏I��
                blockHeader.setStartString(startString);
                blockHeader.setValueMapping(valuemappings);
            }

            public void charactersTableblock(char[] ch, int start, int length)
                    throws SAXException {
            }

            public void ignorableWhitespaceTableblock(char[] ch, int start,
                    int length) throws SAXException {
            }

            public void startElementTablecolumn(String uri, String localName,
                    String qName, String attrName) throws SAXException {
                tableColumn = new BlancoCalcParserTableColumn(attrName);
            }

            public void endElementTablecolumn(String uri, String localName,
                    String qName) throws SAXException {
                final String[] values = new String[valueList.size()];
                valueList.toArray(values);
                valueList.clear();
                tableColumn.setColumnString(values);

                ((BlancoCalcParserTableBlock) blockHeader).add(tableColumn);
            }

            public void charactersTablecolumn(char[] ch, int start, int length)
                    throws SAXException {
            }

            public void ignorableWhitespaceTablecolumn(char[] ch, int start,
                    int length) throws SAXException {
            }
        });

        final TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(new StreamSource(inStreamDef), result);
    }

    /**
     * �u���b�N��ǉ����܂��B
     * 
     * @param block
     *            �ǉ��������u���b�N�I�u�W�F�N�g�B
     */
    public void add(final AbstractBlancoCalcParserBlock block) {
        listBlock.add(block);
    }

    protected void startSheet(String sheetName) throws SAXException {
        if (IS_DEBUG)
            System.out.println("�V�[�g[" + sheetName + "]������...");
    }

    protected void endSheet(Sheet sheet) throws SAXException {
        if (currentBlock != null) {
            getContentHandler().endElement("", currentBlock.getName(),
                    currentBlock.getName());
            currentBlock = null;
        }

        waitForValueX = -1;
        waitForValueY = -1;
        waitForIteratorTitleSearchY = -1;
        currentKeyMapItem = null;
    }

    protected void startRow(int row) throws SAXException {
        isNoCellExistOnRow = true;
        isFirstIteratorRowItem = true;
    }

    protected void endRow(int row) throws SAXException {
        waitForValueX = -1;

        if (waitForIteratorTitleSearchY >= 0) {
            // �^�C�g���s�T�[�`�̏ꍇ�ɂ́A�����ɓ���B
            // ��Ԃ����ł���A�J�E���g�_�E���͎��{���܂��B
            waitForIteratorTitleSearchY--;
        }

        if (currentBlock instanceof BlancoCalcParserTableBlock) {
            if (isFirstIteratorRowItem == false) {
                final BlancoCalcParserTableBlock blockLook = (BlancoCalcParserTableBlock) currentBlock;
                if (blockLook.getRowName() != null
                        && blockLook.getRowName().length() > 0) {
                    // �u���b�N�̌J��Ԃ����ڂɃG���e�B�e�B��ǉ�
                    getContentHandler().endElement("", blockLook.getRowName(),
                            blockLook.getRowName());
                }
            }
        }

        if (isNoCellExistOnRow) {
            if (waitForValueY >= 0) {
                waitForValueY--;
            }
            if (waitForValueY <= 0) {
                if (currentBlock != null) {
                    if (IS_DEBUG)
                        System.out.println("�u���b�N[" + currentBlock.getName()
                                + "]���I�����܂�.");
                    getContentHandler().endElement("", currentBlock.getName(),
                            currentBlock.getName());

                    currentBlock = null;
                }
            }
        }

        if (currentBlock == null) {
            return;
        }

        if (currentBlock instanceof BlancoCalcParserPropertyBlock) {
            currentKeyMapItem = null;
        }
    }

    protected void startColumn(int column) throws SAXException {
    }

    protected void endColumn(int column) throws SAXException {
        if (waitForValueX >= 0) {
            waitForValueX--;
        }

        if (currentBlock == null) {
            return;
        }

        if (waitForValueX < 0) {
            if (currentBlock instanceof BlancoCalcParserPropertyBlock) {
                currentKeyMapItem = null;
            }
        }
    }

    /**
     * �Z�����p�[�X���ꂽ�ۂɌĂяo����܂��B
     * 
     * @param column
     *            �����ł�1�I���W���ŌĂяo����܂��B
     * @param row
     *            �����ł�1�I���W���ŌĂяo����܂��B
     * @param cellValue
     *            �Z���̒l�B
     * @throws SAXException
     *             SAX��O�����������ꍇ�B
     */
    @SuppressWarnings("deprecation")
    protected void fireCell(final int column, final int row,
            final String cellValue) throws SAXException {

        if (cellValue.length() == 0) {
            return;
        }

        // ���Ȃ��Ƃ����̂𔭌����܂����B
        isNoCellExistOnRow = false;

        if (currentBlock != null) {
            if (currentBlock.isEndString(cellValue)) {
                // "�Ɩ�"�𔭌������̂ŋƖ��u���b�N��͂ɓ���܂��B
                if (IS_DEBUG)
                    System.out.println("�u���b�N[" + currentBlock.getName()
                            + "]�̋����I��������(" + cellValue + ")�𔭌�");

                getContentHandler().endElement("", currentBlock.getName(),
                        currentBlock.getName());

                // �J�����g�u���b�N��j��
                currentBlock = null;

            }
        }

        if (currentBlock != null) {
            if (currentBlock instanceof BlancoCalcParserPropertyBlock) {
                if (currentKeyMapItem != null) {
                    final String value = BlancoCalcParserValueMapping.mapping(
                            cellValue, currentBlock.getValueMapping());
                    if (IS_DEBUG)
                        System.out.println("�L�[[" + currentKeyMapItem.getName()
                                + "] = �l[" + value + "]");
                    // �����ŉ�������̋L������
                    saveNode(currentKeyMapItem.getName(), value);
                    return;
                }
            } else if (currentBlock instanceof BlancoCalcParserTableBlock) {
                if (waitForIteratorTitleSearchY <= 0) {
                    final BlancoCalcParserTableBlock blockLook = (BlancoCalcParserTableBlock) currentBlock;
                    final BlancoCalcParserTableColumn item = blockLook
                            .findByColumnPosition(column);
                    if (item == null) {
                        if (IS_DEBUG)
                            System.out
                                    .println("�Y�������(" + column + ")�͔����ł��܂���.");
                        return;
                    }

                    if (isFirstIteratorRowItem) {
                        isFirstIteratorRowItem = false;
                        if (blockLook.getRowName() != null
                                && blockLook.getRowName().length() > 0) {
                            // �u���b�N�̌J��Ԃ����ڂɃG���e�B�e�B��ǉ�
                            getContentHandler().startElement("",
                                    blockLook.getRowName(),
                                    blockLook.getRowName(),
                                    new AttributesImpl());
                        }
                    }

                    // �����ŉ�������̋L������
                    final String value = BlancoCalcParserValueMapping.mapping(
                            cellValue, blockLook.getValueMapping());
                    if (IS_DEBUG)
                        System.out.println("��[" + item.getName() + "] = �l["
                                + value + "]");
                    saveNode(item.getName(), value);
                    return;
                }
            }
        }

        if (IS_DEBUG)
            System.out.println("  (" + BlancoCalcUtil.columnToLabel(column)
                    + row + ")" + cellValue);

        for (int index = 0; index < listBlock.size(); index++) {
            AbstractBlancoCalcParserBlock blockItem = (AbstractBlancoCalcParserBlock) listBlock
                    .get(index);
            if (blockItem.isStartString(cellValue)) {
                // "�Ɩ�"�𔭌������̂ŋƖ��u���b�N��͂ɓ���܂��B
                if (IS_DEBUG)
                    System.out.println("�u���b�N[" + blockItem.getName() + "]�𔭌�");

                final AttributesImpl attrImpl = new AttributesImpl();
                getContentHandler().startElement("", blockItem.getName(),
                        blockItem.getName(), attrImpl);

                // �J�����g�u���b�N���L��
                currentBlock = blockItem;
                waitForValueY = currentBlock.getSearchRangeY();
                if (currentBlock instanceof BlancoCalcParserTableBlock) {
                    BlancoCalcParserTableBlock block = (BlancoCalcParserTableBlock) currentBlock;
                    waitForIteratorTitleSearchY = block
                            .getSearchRangeForTitleY();
                }
            }
        }

        if (currentBlock == null) {
            return;
        }

        if (currentBlock instanceof BlancoCalcParserPropertyBlock) {
            // �L�[��T���������s���܂��B
            final BlancoCalcParserPropertyKey item = ((BlancoCalcParserPropertyBlock) currentBlock)
                    .findByStartString(cellValue);
            if (item != null) {
                currentKeyMapItem = item;
                waitForValueX = item.getSearchRangeX();
            }
        } else if (currentBlock instanceof BlancoCalcParserTableBlock) {
            final BlancoCalcParserTableBlock block = (BlancoCalcParserTableBlock) currentBlock;

            // �^�C�g����T���������s���܂�
            final BlancoCalcParserTableColumn item = (block)
                    .findByTitleString(cellValue);
            if (item != null) {
                // �^�C�g���ʒu���T�[�`�ł��܂����B
                if (IS_DEBUG)
                    System.out.println("��(" + column + ")�̓L�[[" + cellValue
                            + "]�ł�");
                item.setColumnPosition(column);
            } else {
                if (IS_DEBUG)
                    System.out.println("��(" + column + ")�̃L�[[" + cellValue
                            + "]�͓o�^���ꂽ���̂̒��Ɍ�����܂���.");
            }
        }
    }

    /**
     * �m�[�h��ۑ����܂��B
     * 
     * @param key
     *            �L�[�B
     * @param value
     *            �l�B
     * @throws SAXException
     *             SAX��O�����������ꍇ�B
     */
    protected void saveNode(final String key, final String value)
            throws SAXException {
        getContentHandler().startElement("", key, key, new AttributesImpl());

        final char[] charArray = value.toCharArray();

        getContentHandler().characters(charArray, 0, charArray.length);
        getContentHandler().endElement("", key, key);
    }
}
