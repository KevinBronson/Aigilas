﻿using OGUR.Creatures;
using OGUR.Skills;
using SPX.Core;
using System;

namespace OGUR.Strategies
{
    public class AttackStrategy : IStrategy
    {
        private int _skillCooldown = 0;
        private int _skillCooldownMax = 10;

        public AttackStrategy(ICreature parent,params int[] targetTypes)
            : base(parent,Strategy.Attack)
        {
            foreach (var targetType in targetTypes)
            {
                _targets.AddTargetTypes(targetType);
            }
        }

        public override void Act()
        {
            if (AbleToMove())
            {
                _skillCooldown--;
                if (_skillCooldown <= 0)
                {
                    _parent.CycleActiveSkill(1);
                    if (SkillFactory.IsSkill(_parent.GetActiveSkillName(), AnimationType.RANGED))
                    {
                        if (opponent != null)
                        {
                            _parent.SetSkillVector(CalculateTargetVector(_parent.GetLocation(), opponent.GetLocation()));
                        }
                        if (_parent.GetSkillVector().GridX != 0 || _parent.GetSkillVector().GridY != 0)
                        {
                            _parent.UseActiveSkill();
                        }
                    }
                    else
                    {
                        _parent.UseActiveSkill();
                    }
                    _skillCooldown = _skillCooldownMax;
                }
                if (targetPath.HasMoves())
                {
                    nextMove.Copy(targetPath.GetNextMove());
                    _parent.MoveTo(nextMove);
                }
            }
        }
    }
}