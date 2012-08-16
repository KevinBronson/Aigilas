package com.aigilas.skills.impl;import com.aigilas.creatures.CreatureFactory;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;import com.spx.entities.EntityManager;    public class HypothermiaSkill  extends  ISkill    {        public HypothermiaSkill()            { super(SkillId.DISMEMBERMENT, AnimationType.SELF);            Add(Elements.WATER);            AddCost(StatType.MANA, 3);        }@Override        public  void Activate(ICreature target)        {            for (int ii = 0; ii < 4; ii++)            {                CreatureFactory.CreateMinion(SkillId.ICE_SHARD, target, _behavior.GetGraphic(), EntityManager.GetEmptyLocation());            }        }    }