package com.spx.text;
    public class ActionText extends Text
    {

        protected int _lifeSpan = 0;
        protected float _scalePercent = 1;
        public ActionText(String contents, int lifeSpan, int x, int y)
            {
        }
        public ActionText()
        {

        }
        public void Reset(String contents, int lifespan, int x, int y)
        {
            _scalePercent = 1;
            _lifeSpan = lifespan;
            Reset(contents, x, y);
        }
        public int Update()
        {
            _scalePercent *= .98f;
            return _lifeSpan--;
        }
        public void Draw()
        {
            float fontCenter = TextManager.GetFont().MeasureString(_contents) / 2;
            XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position, Color.Black, 0, fontCenter, 1.15f * _scalePercent, SpriteEffects.None, Depth.ActionTextBG);
            XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position, Color.White, 0, fontCenter, 1.0f * _scalePercent, SpriteEffects.None, Depth.ActionText);
        }
    }