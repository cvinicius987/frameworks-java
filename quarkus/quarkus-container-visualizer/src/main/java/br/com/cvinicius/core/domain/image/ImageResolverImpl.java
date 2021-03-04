package br.com.cvinicius.core.domain.image;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.cvinicius.core.helper.ManipulationBytes;
import br.com.cvinicius.infrastructure.engine.Engine;

@ApplicationScoped
class ImageResolverImpl implements ImageResolver{
	
	@Inject
	private Engine engine;
	
	@Override
	public List<ImageVO> listAll(){
			
		var listImages = engine.getClient().listImagesCmd().exec();
		
		return listImages.stream()
					     .map(img -> new ImageVO(img.getId(), img.getParentId(), processName(img.getRepoTags()), ManipulationBytes.convertBytesToMB(img.getSize())))
					     .collect(Collectors.toList());
	}
		
	private String processName(String[] arr){
		return Stream.of(arr).collect(Collectors.joining(" | "));
	} 
}