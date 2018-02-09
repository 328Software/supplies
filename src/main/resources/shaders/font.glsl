#version 330

uniform sampler2D texture_diffuse;

in vec4 pass_Color;
in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {
	    out_Color = texture(texture_diffuse, pass_TextureCoord); //apply texture
        out_Color = out_Color.rrrr; //set all channels to red channel
	    out_Color = mix(out_Color, pass_Color, out_Color[0] != 0); //if out color is zero then use it, else use background color
}