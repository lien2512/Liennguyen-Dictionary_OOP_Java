
    public class Addition extends BinaryExpression{

        public Addition(Expression left,Expression right) {
            super(left, right);
        }

        @Override
        public Expression left(){
            return super.left();
        }

        @Override
        public Expression right() {
            return super.right();
        }

        @Override
        public String toString() {
            return String.format("(%s + %s)", left , right);
        }

        @Override
        public int evaluate() {
            return right.evaluate() + left.evaluate();
        }

    }

