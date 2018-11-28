package com.nowcoder.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.BooleanExpression;
import org.apache.commons.lang.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SensitiveService implements InitializingBean {
    private static final Logger logger= LoggerFactory.getLogger(SensitiveService.class);
    private TrieNode root;
    public  SensitiveService(){
        root=new TrieNode();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        String temp=null;
        List <String> list=new ArrayList<>();
        try{
            InputStream is=
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
            InputStreamReader reader=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(reader);
            while((temp=br.readLine())!=null){
                temp=temp.trim();
                addWord(temp);
            }

            //只用关闭 reader.close;
            reader.close();
        }catch (Exception e){
            logger.error("读取敏感词失败");
        }
    }
    public String filter(String text){
        if(StringUtils.isEmpty(text)){
            return text;
        }
        StringBuilder res=new StringBuilder();
        String replacement="***";
        int begin=0;
        int cur=0;
        TrieNode tempNode=root;
        while(cur<text.length()){

            char c=text.charAt(cur);
            if(isNotProperty(c)){
                if(tempNode==root){
                    res.append(c);
                    begin++;
                }

                cur++;
                continue;
            }
            tempNode=tempNode.getSubNode(c);
            //字典树中就没有开头的单词
            if(tempNode==null){
                res.append(text.charAt(begin));
                cur=cur+1;
                begin=cur;
                //字典树指针初始化
                tempNode=root;
            }else if(tempNode.isKeywordEnd()){
                //
                res.append(replacement);
                cur=cur+1;
                begin=cur;
                //字典树指针初始化
                tempNode=root;
            }else{
                ++cur;
            }
        }
        res.append(text.substring(begin));
        return res.toString();
    }
    /**
     * 生成敏感词前缀树
     * @param lineTxt
     */
    public  void addWord(String lineTxt){
        //生成前缀树
        TrieNode trieNode=root;
        for(int i=0;i<lineTxt.length();++i){
            Character character=lineTxt.charAt(i);
            TrieNode node=trieNode.getSubNode(character);
            if(node==null){
                trieNode.addSubNode(character,new TrieNode());
            }
            //指针移动到下一个
            node=trieNode.getSubNode(character);
            trieNode=node;
        }
        trieNode.setEnd(true);
    }

    /**
     * 过滤特殊标志的词汇
     * @param c
     * @return
     */
    public Boolean isNotProperty(char c){
            //东亚文字
        int ic=(int)c;
        return  !CharUtils.isAsciiAlphanumeric(c)&&(ic<0x2E80||ic>0x9FFF);
    }
    /**
     * 前缀树对的数据结构
     */
    private class TrieNode{
        //是不是关键词结尾
        private boolean end=false;

        //当前节点的所有节点
        private Map<Character,TrieNode> subNode=new HashMap<Character, TrieNode>();

        //放入一个节点
        public void addSubNode(Character key, TrieNode trieNode){
            subNode.put(key,trieNode);
        }
        //根据一个边获取一个节点
        public TrieNode getSubNode(Character key){
            return subNode.get(key);
        }
        public Boolean isKeywordEnd(){

            return end;
        }
        public void setEnd(Boolean judgEnd){
            end=judgEnd;
        }

    }

}
