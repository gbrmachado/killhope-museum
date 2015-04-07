package uk.ac.dur.group1.killhope_museum;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by David on 07/04/2015.
 */
public class Glossary
{
    private Map<String, String> aliases = new HashMap<>();
    private Map<String, String> entries = new HashMap<>();

    public Glossary() {}

    public Iterable<String> getAllLinkableWords()
    {
        Set<String> keys = new HashSet<>(entries.keySet());
        keys.addAll(aliases.keySet());
        return keys;
    }

    public Iterable<String> getAllGlossaryEntries()
    {
        return new HashSet<>(entries.keySet());
    }

    public void addEntry(String key, String value)
    {
        if(key == null)
            throw new IllegalArgumentException("key is null");

        if(value == null)
            throw new IllegalArgumentException("value is null");

        entries.put(key, value);
    }

    public void addAliases(String aliasTo, Iterable<String> aliases)
    {
        if(aliasTo == null)
            throw new IllegalArgumentException("key is null");

        for (String alias : aliases)
        {
            if(alias == null)
            {
                Log.i("Glossary",String.format("a supplied alias for key: %s was null", aliasTo));
                continue;
            }
            addAliasInternal(alias, aliasTo);
        }
    }

    public void addAlias(String key, String aliasTo)
    {
        if(key == null)
            throw new IllegalArgumentException("key is null");

        if(aliasTo == null)
            throw new IllegalArgumentException("aliasTo is null");

        addAliasInternal(key, aliasTo);
    }

    /**
     *
     * @param key The reference
     * @param aliasTo The original key.
     */
    private void addAliasInternal(String key, String aliasTo)
    {
        if(!entries.containsKey(aliasTo))
            throw new IllegalArgumentException(String.format("The key to alias: %s to is not found.",aliasTo, key));

        aliases.put(key, aliasTo);
    }

    /**
     *
     * @param name The entry name or alias to look up.
     * @return The definition for the entry, or null if the entry does not exist.
     */
    public String lookupEntry(String name) {
        if(entries.containsKey(name))
            return entries.get(name);

        //Get the entry from the alias, then the value from that.
        if(aliases.containsKey(name))
            return entries.get(aliases.get(name));

        //Nothing found
        return null;
    }
}
