﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using SPX.Core;

namespace SPX.Paths
{

    public class Path
    {
        public Point2 Finish = new Point2(0, 0);
        private List<Point2> _steps = new List<Point2>();
        private Dictionary<Point2,Point2> _stepLookup = new Dictionary<Point2,Point2>();
        private float _totalWeight = 0;

        public Path(){}

        public Path Reset(Point2 start, Point2 finish)
        {
            _steps.Clear();
            _stepLookup.Clear();
            _totalWeight = 0;
            moveIndex = -1;
            Finish.Copy(finish);
            Add(start);
            return this;
        }

        public Path Copy(Path source)
        {
            if (source != null)
            {
                _stepLookup = StepLookup.Copy(source._stepLookup);
                _steps = Walk.Copy(source._steps);
                _totalWeight = source._totalWeight;
                Finish.Copy(source.Finish);
            }
            moveIndex = -1;
            return this;
        }

        public bool Add(Point2 step)
        {
            if(!_stepLookup.ContainsKey(step))
            {
                _stepLookup.Add(step, step);
                _steps.Add(step);
                _totalWeight += step.Weight;
                return true;
            }
            return false;
        }
        public float GetCost()
        {
            return _totalWeight;
        }

        private int moveIndex = -1;
        public bool HasMoves()
        {
            return moveIndex < _steps.Count();
        }
        public Point2 GetNextMove()
        {
            moveIndex++;
            if (moveIndex >= _steps.Count())
            {
                return null;
            }
            if (_steps.Count() == 0)
            {
                return null;
            }
            return _steps.Count == 1 ? _steps[0] : _steps[moveIndex];
        }

        public bool IsDone()
        {
            return _stepLookup.ContainsKey(Finish);
        }
        public Point2 GetLastStep()
        {
            return _steps[_steps.Count - 1];
        }

        public List<Point2> GetNeighbors()
        {
           return GetLastStep().GetNeighbors();
        }

        public int Length()
        {
            return _steps.Count;
        }
    }
}