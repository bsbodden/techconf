package com.integrallis.techconf.web.tapestry.components;

import java.util.List;

import org.apache.tapestry.AbstractComponent;
import org.apache.tapestry.IMarkupWriter;
import org.apache.tapestry.IRender;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.ComponentClass;
import org.apache.tapestry.annotations.Parameter;
import org.apache.tapestry.valid.IValidationDelegate;

/**
 * This will display the errors for the page.
 *  
 * @author Joseph Nusairat
 */
@ComponentClass(allowBody = false, allowInformalParameters = false)
public abstract class ErrorDisplay extends AbstractComponent {
	
	@Parameter(required = true)
    public abstract IValidationDelegate getDelegate();
	
	/**
	 * Renders the actual error messages.
	 */
    @SuppressWarnings("unchecked")
    public void renderComponent(IMarkupWriter writer, IRequestCycle cycle) {
        if (cycle.isRewinding())
            return;

        IValidationDelegate delegate = getDelegate();

        if (delegate != null) {
	        if (!delegate.getHasErrors())
	            return;
	
	        writer.begin("table");
	        writer.attribute("class", "red");
	
	        writer.begin("tr");
	        writer.attribute("valign", "top");
	
	        writer.begin("td");
	        writer.beginEmpty("img");
	        writer.attribute("src", "../images/warning.png");
	        writer.attribute("width", 42);
	        writer.attribute("height", 42);
	        writer.end();	        
	
	        writer.begin("td");
	        writer.attribute("class", "message");
	        List<IRender> errorRenders = delegate.getErrorRenderers();
	        
	        writer.begin("ul");
	        for (IRender render : errorRenders) {
	        	writer.begin("li");
	        	render.render(writer, cycle);
	        	writer.end();	        	
	        }
	        writer.end();	
	
	        writer.end("table");
        }
    }
}
