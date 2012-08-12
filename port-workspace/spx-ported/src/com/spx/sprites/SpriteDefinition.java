package com.spx.sprites;

public class SpriteDefinition
{
    public SpriteDefinition(int type, int index, int frames)
    {
        Type = type;
        Info = new SpriteInfo(index, frames);
    }
    public int Type;
    public SpriteInfo Info; 
}