package com.aigilas.reactions.impl;import com.aigilas.creatures.ICreature;import com.aigilas.reactions.IReaction;import com.aigilas.statuses.Status;import com.aigilas.statuses.StatusFactory;    public class VentReaction  implements IReaction    {        @Override		public void Affect(ICreature target)        {            StatusFactory.Apply(target, Status.SlowDown);        }    }