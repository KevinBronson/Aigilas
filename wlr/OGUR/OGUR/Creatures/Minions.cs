﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using OGUR.Sprites;
using OGUR.Strategies;
using OGUR.GameObjects;

namespace OGUR.Creatures
{
    class Minion : ICreature
    {
        public Minion(int creatureType = CreatureType.MINION,float coolDown = Stats.DefaultCoolDown)
        {
            m_creatureType = creatureType;
            m_baseStats = new Stats(80f,999f,0f,0f,0f,0f,0f,0f,0f,coolDown);
        }
        public void Init(ICreature source)
        {
            m_master = source;
            Setup(source.GetLocation(), m_creatureType, m_baseStats,null,false);
            SetSkillVector(new Collision.Point2(1, 0));
            m_strategy = new MinionRotate(this);
        }
        protected void Add(string skill)
        {
            if (m_skills == null)
            {
                m_skills = new SkillPool(this);
            }
            m_skills.Add(skill);
        }
    }

    class AcidNozzle : Minion
    {
        public AcidNozzle()
            : base(CreatureType.MINION,50f)
        {
            Add(SkillId.ACID_DRIP);
            m_composition.Add(Elements.EARTH);
        }
    }
}
