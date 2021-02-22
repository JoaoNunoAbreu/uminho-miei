%option noyywrap yylineno
%x MD ABC P

%%
<*>title:               {BEGIN MD;}
<*>\<abc\>              {BEGIN ABC;}
<*>\<\/abc\>            {BEGIN P;}
<MD>\n\n                {BEGIN P;}
<MD>.|n                 {}
<ABC>.|n                {}
<P>_                    {}
<P>\ \ .*               {}
%%

int main(int argc, char* argv[]){
    yylex();
    return 0;
}