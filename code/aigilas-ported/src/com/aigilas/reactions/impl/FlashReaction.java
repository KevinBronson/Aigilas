package com.aigilas.reactions.impl;import com.aigilas.creatures.ICreature;import com.aigilas.reactions.IReaction;import com.spx.entities.EntityManager;    public class FlashReaction  implements IReaction    {        @Override		public void Affect(ICreature target)        {            target.SetLocation(EntityManager.GetEmptyLocation());        }    }