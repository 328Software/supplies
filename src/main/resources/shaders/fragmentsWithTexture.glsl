#version 330

uniform sampler2D texture_diffuse;

in vec4 pass_Color;
in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {
	out_Color = pass_Color;
	// Override out_Color with our texture pixel
	if(pass_TextureCoord[0] >= 0) {
	    out_Color = texture2D(texture_diffuse, pass_TextureCoord);
	    out_Color = mix(pass_Color, out_Color, out_Color.a);
	}
}