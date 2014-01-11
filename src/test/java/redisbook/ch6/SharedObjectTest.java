///******************************************************
// * author : Kris Jeong
// * published : 2013. 7. 8.
// * project name : redis-book
// *
// * Email : smufu@naver.com, smufu1@hotmail.com
// *
// * Develop JDK Ver. 1.6.0_13
// *
// * Issue list.
// *
// * 	function.
// *     1.
// *
// ********** process *********
// *
// ********** edited **********
// *
// ******************************************************/
//package redisbook.ch6;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import redisbook.JedisHelper;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * @author <A HREF="mailto:smufu@naver.com">kris jeong</A> smufu@naver.com
// *         <p/>
// *         class desc
// */
//public class SharedObjectTest {
//    static JedisHelper helper;
//
//    private SharedObject sharedObject;
//
//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//        helper = JedisHelper.getInstance();
//    }
//
//    @AfterClass
//    public static void tearDownAfterClass() throws Exception {
//        helper.destoryPool();
//    }
//
//    @Test
//    public void testSharedObject() {
//        this.sharedObject = new SharedObject(helper);
//
//        this.sharedObject.prepareRedis();
//        this.sharedObject.makeIntegerData();
//        assertEquals(this.sharedObject.getNumberOfKeys(), this.sharedObject.getSharedLimit());
//        this.sharedObject.getMemoryInfo();
//    }
//
//}
