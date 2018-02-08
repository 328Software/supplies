#version 330

uniform sampler2D texture_diffuse;

in vec4 pass_Color;
in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {
//	out_Color = pass_Color;
	// Override out_Color with our texture pixel
//	if(pass_TextureCoord[0] >= 0) {
//	    vec4 tmp = vec4(pass_Color[0], pass_Color[1], pass_Color[0], pass_Color[3]);
	    out_Color = texture(texture_diffuse, pass_TextureCoord);
	    out_Color[1] = out_Color[0];
	    out_Color[2] = out_Color[0];
	    out_Color[3] = out_Color[0];

	    out_Color = mix(out_Color, pass_Color, out_Color[0] != 0);
//	}
}