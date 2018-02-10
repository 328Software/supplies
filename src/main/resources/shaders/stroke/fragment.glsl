#version 330

uniform sampler2D texture_diffuse;

in vec4 pass_Color;
in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {


    out_Color = texture(texture_diffuse, pass_TextureCoord); //apply texture

    out_Color = out_Color.rrrr; //set all channels to red channel
    out_Color = mix(out_Color, pass_Color, out_Color[0] != 0); //if out color is zero then use it, else use background color
//    if(out_Color.a == 0.0f) {
    ivec2 size = textureSize(texture_diffuse, 0);

    float alpha = 0.0f;
    float x_off = 2f/size.x, y_off = 2f/size.y;
//    alpha += texture( texture_diffuse, pass_TextureCoord + vec2(  x_off,      0.0f ) ).r;
//    alpha += texture( texture_diffuse, pass_TextureCoord + vec2( -x_off,      0.0f ) ).r;
//    alpha += texture( texture_diffuse, pass_TextureCoord + vec2(    0.0f,    y_off ) ).r;
//    alpha += texture( texture_diffuse, pass_TextureCoord + vec2(    0.0f,   -y_off ) ).r;
    alpha += texture( texture_diffuse, pass_TextureCoord + vec2( x_off, y_off ) ).r;
    alpha += texture( texture_diffuse, pass_TextureCoord + vec2( -x_off,-y_off ) ).r;
    alpha += texture( texture_diffuse, pass_TextureCoord + vec2( x_off, -y_off ) ).r;
    alpha += texture( texture_diffuse, pass_TextureCoord + vec2( -x_off, y_off ) ).r;

    vec4 stroke = vec4(1.0f, 1.0f, 1.0f, alpha > 0);

    out_Color = mix(out_Color, stroke, out_Color.a == 0 && alpha > 0);
//    }

}