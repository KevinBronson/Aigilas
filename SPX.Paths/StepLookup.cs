﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Paths
{
    class StepLookup
    {
        private static Dictionary<Point2,Point2>[] __lookups = new Dictionary<Point2,Point2>[1000];
        private static int _index = 0;

        public static Dictionary<Point2,Point2> Copy(Dictionary<Point2,Point2> walk)
        {
            if (__lookups[0] == null)
            {
                for (int ii = 0; ii < __lookups.Count(); ii++)
                {
                    __lookups[ii] = new Dictionary<Point2,Point2>(200);
                }
            }
            _index = (_index + 1) % __lookups.Count();
            __lookups[_index].Clear();
            foreach (var keyval in walk)
            {
                __lookups[_index].Add(keyval.Key,keyval.Value);
            }
            return __lookups[_index];
        }
    }
}