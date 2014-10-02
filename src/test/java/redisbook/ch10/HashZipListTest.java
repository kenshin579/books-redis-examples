/******************************************************
 * author : Kris Jeong
 * published : 2013. 6. 30.
 * project name : redis-book
 *
 * Email : smufu@naver.com, smufu1@hotmail.com
 *
 * Develop JDK Ver. 1.6.0_13
 *
 * Issue list.
 *
 * 	function.
 *     1. 
 *
 ********** process *********
 *
 ********** edited **********
 *
 ******************************************************/
package redisbook.ch10;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import redisbook.util.JedisHelper;

import static org.junit.Assert.assertEquals;


/**
 * @author <A HREF="mailto:smufu@naver.com">kris jeong</A> smufu@naver.com
 *         <p/>
 *         class desc
 */
public class HashZipListTest {
    static JedisHelper helper;

    private HashZipList hashZipList;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        helper = JedisHelper.getInstance();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        helper.destoryPool();
    }

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.hashZipList = new HashZipList(helper);
    }

    @Test
    public void testEncodingTestForEntrySize() {
        assertEquals("ziplist", this.hashZipList.getBeforeEncoding1());

        assertEquals("hashtable", this.hashZipList.getAfterEncoding1());
    }

    @Test
    public void testUnlimitedEncoding() {
        assertEquals("ziplist", this.hashZipList.getBeforeEncoding1("11", 2));
    }

    @Test
    public void test_다른_key끼리는_encoding방식에_서로_영향을_주지_않는지_확인() {
        assertEquals("ziplist", this.hashZipList.getBeforeEncoding1("101", 512));

        assertEquals("hashtable", this.hashZipList.getBeforeEncoding1("102", 513));
    }

    @Test
    public void testEncodingTestForDataSize() {
        assertEquals("ziplist", this.hashZipList.getBeforeEncoding2());

        assertEquals("hashtable", this.hashZipList.getAfterEncoding2());
    }

}
