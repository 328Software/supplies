#version 330

uniform sampler2D texture_diffuse;

in vec4 pass_Color;
in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {
	if(pass_TextureCoord[0] >= 0) {
	    float temp = texture2D(texture_diffuse, pass_TextureCoord).r;
	    out_Color = vec4(pass_Color.rgb, temp);
	} else {
    	out_Color = pass_Color;
	}
}