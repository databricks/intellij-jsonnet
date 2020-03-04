package com.jsonnetplugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.jsonnetplugin.psi.JsonnetTypes.*;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import com.intellij.psi.TokenType;

%%

%class JsonnetLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

WHITE_SPACE=\s+

LINE_COMMENT="//"[^\n]*|"#"[^\n]*
BLOCK_COMMENT="/"\*([^*]|\*+[^*/])*(\*+"/")?
DOUBLE_QUOTED_STRING=\"([^\\\"]|\\[^\r\n])*\"?
SINGLE_QUOTED_STRING='([^\\']|\\[^\r\n])*'?
VERBATIM_DOUBLE_QUOTED_STRING=@\"([^\"]|\"\")*\"?
VERBATIM_SINGLE_QUOTED_STRING=@'([^']|'')*'?
NUMBER=((0|[1-9][0-9]*)(\.[0-9]+)?([eE][+-]?[0-9]*)?)|Infinity|-Infinity|NaN
TRIPLE_BAR_QUOTED_STRING=\|\|\|\s*\n\s+(\|[^\|]|\|\|[^\|]|[^\|])*\|\|\|
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return WHITE_SPACE; }

  "{"                         { return L_CURLY; }
  "}"                         { return R_CURLY; }
  "["                         { return L_BRACKET; }
  "]"                         { return R_BRACKET; }
  "("                         { return L_PAREN; }
  ")"                         { return R_PAREN; }
  ","                         { return COMMA; }
  "."                         { return DOT; }
  ";"                         { return SEMICOLON; }
  "="                         { return EQUAL; }
  ":"                         { return COLON; }
  "::"                        { return COLON2; }
  ":::"                       { return COLON3; }
  "true"                      { return TRUE; }
  "false"                     { return FALSE; }
  "null"                      { return NULL; }
  "import"                    { return IMPORT; }
  "importstr"                 { return IMPORTSTR; }
  "local"                     { return LOCAL; }
  "*"                         { return ASTERISK; }
  "/"                         { return SLASH; }
  "%"                         { return PERCENT; }
  "+"                         { return PLUS; }
  "-"                         { return MINUS; }
  "<<"                        { return DOUBLE_LESS; }
  ">>"                        { return DOUBLE_GREATER; }
  "<"                         { return LESS_THAN; }
  "<="                        { return LESS_EQUAL; }
  ">"                         { return GREATER_THAN; }
  ">="                        { return GREATER_EQUAL; }
  "=="                        { return DOUBLE_EQUAL; }
  "!="                        { return NOT_EQUAL; }
  "in"                        { return IN; }
  "&"                         { return AND; }
  "^"                         { return CARAT; }
  "|"                         { return BAR; }
  "&&"                        { return DOUBLE_AND; }
  "||"                        { return DOUBLE_BAR; }
  "!"                         { return EXCLAMATION; }
  "~"                         { return TILDE; }
  "function"                  { return FUNCTION; }
  "if"                        { return IF; }
  "then"                      { return THEN; }
  "else"                      { return ELSE; }
  "super"                     { return SUPER; }
  "error"                     { return ERROR; }
  "self"                      { return SELF; }
  "for"                       { return FOR; }
  "$"                         { return DOLLAR; }
  "assert"                    { return ASSERT; }
  {LINE_COMMENT}              { return LINE_COMMENT; }
  {BLOCK_COMMENT}             { return BLOCK_COMMENT; }
  {DOUBLE_QUOTED_STRING}      { return DOUBLE_QUOTED_STRING; }
  {SINGLE_QUOTED_STRING}      { return SINGLE_QUOTED_STRING; }
  {VERBATIM_DOUBLE_QUOTED_STRING} { return VERBATIM_DOUBLE_QUOTED_STRING; }
  {VERBATIM_SINGLE_QUOTED_STRING} { return VERBATIM_SINGLE_QUOTED_STRING; }
  {TRIPLE_BAR_QUOTED_STRING}  { return TRIPLE_BAR_QUOTED_STRING; }
  {NUMBER}                    { return NUMBER; }
  {IDENTIFIER}                { return IDENTIFIER; }

}

[^] { return BAD_CHARACTER; }

//id=[_a-zA-Z][_a-zA-Z0-9]*
//escape=\\[\"\'\\\/bfnrt]
//string=\"([^\\\"]|{escape})*\"|\'([^\\\']|{escape})*\'|@\"([^\"]|\"\")*\"|@\'([^\']|\'\')*\'
//number=-?(?:0|[1-9]\d*)(?:\.\d+)?(?:[eE][+-]?\d+)?



//CRLF=\R
//WHITE_SPACE=[\ \n\t\f]
//FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
//VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
//END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
//SEPARATOR=[:=]
//KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "
//
//%state WAITING_VALUE
//
//%%
//
//<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return JsonnetTypes.COMMENT; }
//
//<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return JsonnetTypes.KEY; }
//
//<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return JsonnetTypes.SEPARATOR; }
//
//<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return JsonnetTypes.VALUE; }
//
//({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//
//.                                                           { return TokenType.BAD_CHARACTER; }
