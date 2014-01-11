/******************************************************
 * author : Kris Jeong
 * published : 2013. 6. 15.
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
package redisbook.ch8.replication;


import redisbook.ch7.redislogger.KeyMaker;

/**
 * @author <A HREF="mailto:smufu@naver.com">kris jeong</A> smufu@naver.com
 *         <p/>
 *         class desc
 */
public class ReplicationKeyMakerV2 implements KeyMaker {
    private static final String keyPrefix = "Replication List-";

    private int index;

    /**
     * 키 메이커 클래스를 위한 생성자.
     *
     * @param index 키 생성을 위한 인덱스 값
     */
    public ReplicationKeyMakerV2(int index) {
        this.index = index;
    }

    /* (non-Javadoc)
     * @see net.sf.redisbook.ch7.redislogger.KeyMaker#getKey()
     */
    @Override
    public String getKey() {
        return keyPrefix + this.index;
    }
}
