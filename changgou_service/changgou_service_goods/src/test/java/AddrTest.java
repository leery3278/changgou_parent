import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import org.junit.Test;

public class AddrTest {
    @Test
    public void test() {
        String addr = "浦东新区申波路292号";
        //自动根据用户引入的分词库的jar来自动选择使用的引擎
        TokenizerEngine engine = TokenizerUtil.createEngine();
        //解析文件
        Result result = engine.parse(addr);
        String resultStr = CollUtil.join((Iterable<Word>) result," ");
        System.out.println(resultStr);
    }
}
