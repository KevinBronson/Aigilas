package com.aigilas.skills.impl;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;    public class AcidDripSkill  extends  ISkill    {        public AcidDripSkill()            { super(SkillId.ACID_DRIP, AnimationType.STATIONARY);            StartOffCenter = true;            AddCost(StatType.MANA, 10);            Add(Elements.WATER);        }@Override        public  void Affect(ICreature target)        {            target.ApplyDamage(20, _source);        }    }