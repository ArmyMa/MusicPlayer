package com.warchaser.musicplayer.tools;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Wucn on 2017/1/26.
 *
 */

public class CallObserver
{
    private static ArrayList<UIObserver> mObservers = new ArrayList<UIObserver>();

    private CallObserver()
    {

    }

    public static void setObserver(UIObserver observer)
    {
        mObservers.add(observer);
    }

    public synchronized static void callObserver(Intent intent)
    {
        int size;
        if(mObservers != null && (size = mObservers.size()) > 0)
        {

            for(int i = 0; i < size; i++)
            {
                UIObserver observer = mObservers.get(i);
                if(observer != null && observer.getObserverEnabled())
                {
                    observer.notifySeekBar2Update(intent);
                }
            }
        }
    }

    /**
     * 通知UI更新逻辑
     * @param repeatTime 1 重复一次(playOrPause); 2重复两次(next)
     * @return boolean 是否存在UI Observer
     * */
    public synchronized static boolean callPlay(int repeatTime)
    {
        int size;
        boolean isOneUIEnabled = false;
        if(mObservers != null && (size = mObservers.size()) > 0)
        {
            for(int i = 0; i < size; i++)
            {
                UIObserver observer = mObservers.get(i);
                if(observer != null && observer.getObserverEnabled())
                {
                    observer.notify2Play(repeatTime);
                    isOneUIEnabled = true;
                }
            }
        }

        return isOneUIEnabled;
    }

    public static void removeAllObservers()
    {
        if(mObservers != null)
        {
            mObservers.clear();
        }
    }

    public static void removeSingleObserver(UIObserver observer)
    {
        if(mObservers != null)
        {
            if(mObservers.contains(observer))
            {
                mObservers.remove(observer);
            }
        }
    }

}
