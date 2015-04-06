package uk.ac.dur.group1.killhope_museum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 06/04/2015.
 */
public class GlossaryProvider
{
    private GlossaryProvider() {}

    public static Map<String, String> getGlossary()
    {
        HashMap<String,String> ret = new HashMap<>();
        ret.put("aa","bb");
        ret.put("as","this");
        ret.put("zz","yz");
        return ret;
    }


}
