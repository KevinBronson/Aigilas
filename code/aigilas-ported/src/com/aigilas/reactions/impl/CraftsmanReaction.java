package com.aigilas.reactions.impl;import com.aigilas.creatures.ICreature;import com.aigilas.items.ItemFactory;import com.aigilas.reactions.IReaction;    public class CraftsmanReaction  implements IReaction    {        @Override		public void Affect(ICreature target)        {            try {				ItemFactory.CreateRandomPlain(target.GetLocation());			} catch (Exception e) {								e.printStackTrace();			}        }    }