/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordapplication.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import passwordapplication.models.Whiteword;
import passwordapplication.models.Word;
import passwordapplication.models.Wordlist;

/**
 *
 * @author antti
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class WhitewordDaoTest {

    @Mock
    Whiteword mockword;

    @Mock
    Wordlist mockwordlist;

    @Mock
    JdbcTemplate mockjdbcTemplate;
    
    @InjectMocks
    private WhitewordDAO whiteworddao = new WhitewordDAO();

    
    @Test
    public void TestInsert() {
        when(mockword.getWord()).thenReturn("mock");
        when(mockword.getActive()).thenReturn(true);
        when(mockword.getWordlist()).thenReturn(mockwordlist);
        when(mockwordlist.getId()).thenReturn(1);
        try {
            whiteworddao.insert(mockword);
        } catch (SQLException ex) {
            fail("SQL error");
        }
    }

    @Test
    public void TestSecondInsert() {
        try {
            whiteworddao.insert("mock", true, 1);
        } catch (SQLException ex) {
            fail("SQL error");
        }
    }

    @Test
    public void TestRead() {
        when(mockjdbcTemplate.queryForObject(anyString(), any(BeanPropertyRowMapper.class), anyInt())).thenReturn(mockword);
        Whiteword retVal = new Whiteword();
        try {
            retVal = whiteworddao.read(1);
        } catch (SQLException ex) {
            fail("SQL error");
        }
        assertEquals(mockword, retVal);

    }

    @Test
    public void TestReadNameAndActive() {
        Pair mockpair = new Pair("mock", true);
        when(mockjdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(mockpair);
        Pair retVal = null;
        try {
            retVal = whiteworddao.readNameAndActive(1);
        } catch (SQLException ex) {
            fail("SQL error");
        }

        assertEquals(mockpair, retVal);
    }

    @Test
    public void TestDeleteListWords() {
        try {
            whiteworddao.deleteListWords(1);
        } catch (SQLException ex) {
            fail("SQL error");
        }
    }

    @Test
    public void TestSetInactive() {
        try {
            whiteworddao.setInactive("mock");
        } catch (SQLException ex) {
            fail("SQL error");
        }
    }

    @Test
    public void TestSetActive() {
        try {
            whiteworddao.setActive("mock");
        } catch (SQLException ex) {
            fail("SQL error");
        }
    }

    @Test
    public void TestListTenStringsFromList() {
        List<String> mockList = new ArrayList();
        mockList.add("mock");
        mockList.add("list");
        when(mockjdbcTemplate.query(anyString(), any(RowMapper.class), anyInt())).thenReturn(mockList);
        List<String> retVal = null;
        try {
            retVal = whiteworddao.listTenStringsFromList(1);
        } catch (SQLException ex) {
            fail("SQL error");
        }
        assertEquals(mockList, retVal);
    }

    /*
    @Test
    public void TestListNActiveStrings() throws SQLException{
        List<String> mockList = new ArrayList();
        mockList.add("mock");
        mockList.add("mock");
        mockList.add("mock");
        Pair mockpair = new Pair("mock", true);
        when(mockjdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(mockpair);
        when(mockjdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(100);
        //doReturn(mockpair).when(whiteworddao).readNameAndActive(anyInt());
        List<String> retVal = null
                ;
        try {
            retVal = whiteworddao.listNActiveStrings(3);
        } catch (SQLException ex) {
            fail("SQL error");
        }
        assertEquals(mockList, retVal);
    }   
    */
    
    @Test
    public void TestGetListSize(){
        when(mockjdbcTemplate.queryForObject(anyString(), eq(Integer.class), anyInt())).thenReturn(1);
        int retVal = 0;
        try {
            retVal = whiteworddao.getListSize(1);
        } catch (SQLException ex) {
            fail("SQL error");
        }
        assertEquals(1, retVal);
    }
}
