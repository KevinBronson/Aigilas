﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Statuses
{
    public class StatusPool
    {
        private List<IStatus> m_statuses = new List<IStatus>();

        public bool CanMove()
        {
            for (int ii = 0; ii < m_statuses.Count(); ii++)
            {
                if (m_statuses[ii].StopMovement())
                {
                    return false;
                }
            }
            return true;
        }

        public bool CanAttack()
        {
            for (int ii = 0; ii < m_statuses.Count(); ii++)
            {
                if (m_statuses[ii].StopAttack())
                {
                    return false;
                }
            }
            return true;
        }

        public void Add(IStatus status)
        {
            m_statuses.Add(status);
        }

        public void Update()
        {
            for (int ii = 0; ii < m_statuses.Count(); ii++)
            {
                m_statuses[ii].Update();
                if (!m_statuses[ii].IsActive())
                {
                    m_statuses[ii].Cleanup();
                    m_statuses.Remove(m_statuses[ii]);
                    ii--;
                }
            }
        }
    }
}